package com.crawljax.core.configuration;

import javax.annotation.concurrent.Immutable;

/**
 * Class for passing proxy settings to Crawljax' browser builder. It is returned by the
 * ProxyServerPlugin interface.
 */
@Immutable
public class ProxyConfiguration {

	/**
	 * Type of proxy this is.
	 */
	public enum ProxyType {
		/**
		 * Don't use a proxy at all.
		 */
		NOTHING(0),
		/**
		 * Use a manually configured proxy (using your ProxyConfiguration object).
		 */
		MANUAL(1),
		/**
		 * Use an automatically configured proxy (using a pax file for example).
		 */
		AUTOMATIC(4),
		/**
		 * Use the proxy that is configured for your computer (system wide).
		 */
		SYSTEM_DEFAULT(5);

		private int value;

		private ProxyType(int value) {
			this.value = value;
		}

		/**
		 * Converts the enum to an int (used by Firefox internally).
		 * 
		 * @return The int representation of the enum value.
		 */
		public int toInt() {
			return value;
		}
	}

	/**
	 * @see ProxyType#NOTHING
	 */
	public static ProxyConfiguration noProxy() {
		return new ProxyConfiguration(-1, "none", ProxyType.NOTHING);
	}

	/**
	 * @see ProxyType#AUTOMATIC
	 */
	public static ProxyConfiguration automatic() {
		return new ProxyConfiguration(-1, "none", ProxyType.AUTOMATIC);
	}

	/**
	 * @see ProxyType#SYSTEM_DEFAULT
	 */
	public static ProxyConfiguration systemDefault() {
		return new ProxyConfiguration(-1, "none", ProxyType.SYSTEM_DEFAULT);
	}

	/**
	 * @see ProxyType#MANUAL
	 */
	public static ProxyConfiguration manualProxyOn(String host, int port) {
		return new ProxyConfiguration(port, host, ProxyType.MANUAL);
	}

	private final int port;
	private final String hostname;
	private final ProxyType type;

	private ProxyConfiguration(int port, String hostname, ProxyType type) {
		this.port = port;
		this.hostname = hostname;
		this.type = type;
	}

	/**
	 * @return The port.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return The hostname.
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return The type.
	 */
	public ProxyType getType() {
		return type;
	}

	@Override
	public String toString() {
		return type.toString() + ':' + hostname + ':' + port;
	}
}
