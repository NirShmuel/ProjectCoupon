package connectionPool;

import java.sql.*;
import java.util.*;

/**
 * A class for preallocating, recycling, and managing JDBC connections.
 * 
 * © 2002 Song Jing; may be freely used or adapted.
 */

public class ConnectionPool {// implements Runnable {
	private String driver, url, username, password;
	private int maxConnections;
	private boolean waitIfBusy;
	private List<Connection> availableConnections, busyConnections;
	private boolean connectionPending = false;

	public ConnectionPool(String driver, String url, String username,
			String password, int initialConnections, int maxConnections,
			boolean waitIfBusy) throws SQLException {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		this.maxConnections = maxConnections;
		this.waitIfBusy = waitIfBusy;
		if (initialConnections > maxConnections) {
			initialConnections = maxConnections;
		}
		availableConnections = new ArrayList<Connection>(initialConnections);
		busyConnections = new ArrayList<Connection>();

		// Create the initial connections
		for (int i = 0; i < initialConnections; i++) {
			availableConnections.add(makeNewConnection());
		}
	}

	public synchronized void releaseConnection(Connection connection) {
		busyConnections.remove(connection);
		availableConnections.add(connection);

		// Wake up threads that are waiting for a connection
		notifyAll();
	}

	public synchronized Connection getConnection() throws SQLException {
		if (!availableConnections.isEmpty()) {
			// Get last connection from the list
			int lastIndex = availableConnections.size() - 1;
			Connection con = (Connection) availableConnections
					.remove(lastIndex);

			// If connection on available list is closed (e.g.,
			// it timed out), then remove it from available list
			// and repeat the process of obtaining a connection.
			// Also wake up threads that were waiting for a
			// connection because maxConnection limit was reached.
			if (con.isClosed()) {
				notifyAll(); // Freed up a spot for anybody waiting
				return getConnection();
			} else {
				busyConnections.add(con);
				return con;
			}
		} else {

			// Three possible cases:
			// 1) You haven't reached maxConnections limit. So
			// establish one in the background if there isn't
			// already one pending, then wait for
			// the next available connection (whether or not
			// it was the newly established one).
			// 2) You reached maxConnections limit and waitIfBusy
			// flag is false. Throw SQLException in such a case.
			// 3) You reached maxConnections limit and waitIfBusy
			// flag is true. Then do the same thing as in second
			// part of step 1: wait for next available connection.

			if ((totalConnections() < maxConnections) && !connectionPending) {
				return makeBackgroundConnection();
			} else if (!waitIfBusy) {
				throw new SQLException("Connection limit reached");
			}
			// Wait for either a new connection to be established
			// (if you called makeBackgroundConnection) or for
			// an existing connection to be freed up.
			try {
				wait();
			} catch (InterruptedException ie) {
			}
			// Someone freed up a connection, so try again.
			return getConnection();
		}
	}

	public synchronized int totalConnections() {
		return (availableConnections.size() + busyConnections.size());
	}

	/**
	 * Close all the connections. Use with caution: be sure no connections are
	 * in use before calling. Note that you are not <I>required to call this
	 * when done with a ConnectionPool, since connections are guaranteed to be
	 * closed when garbage collected. But this method gives more control
	 * regarding when the connections are closed.
	 */

	public synchronized void shutdown() {
		closeConnections(availableConnections);
		availableConnections = new ArrayList<Connection>();
		closeConnections(busyConnections);
		busyConnections = new ArrayList<Connection>();
	}

	public synchronized String toString() {
		String info = "ConnectionPool(" + url + "," + username + ")"
				+ ", available=" + availableConnections.size() + ", busy="
				+ busyConnections.size() + ", max=" + maxConnections;
		return (info);
	}

	//
	// Private methods. Under the assumption that they are called
	// from the public synchronized methods. Do not call them from 
	// a non synced method!
	//
	
	
	// This explicitly makes a new connection. Called in
	// the foreground when initializing the ConnectionPool,
	// and called in the background when running.
	private Connection makeNewConnection() throws SQLException {
		try {
			// Load database driver if not already loaded
			Class.forName(driver);
			// Establish network connection to database
			Connection connection = DriverManager.getConnection(url, username,
					password);
			return (connection);
		} catch (ClassNotFoundException cnfe) {
			// Simplify try/catch blocks of people using this by
			// throwing only one exception type.
			throw new SQLException("Can't find class for driver: " + driver);
		}
	}

	private void closeConnections(List<Connection> connections) {
		try {
			for (int i = 0; i < connections.size(); i++) {
				Connection connection = (Connection) connections.get(i);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		} catch (SQLException sqle) {
			// Ignore errors; garbage collect anyhow
			// NO!!!!
		}
	}

	private Connection makeBackgroundConnection() throws SQLException {
		connectionPending = true;

		// Thread connectThread = new Thread(this);
		// connectThread.start();
		Connection connection = makeNewConnection();
		availableConnections.add(connection);
		connectionPending = false;
		return connection;

	}

}
