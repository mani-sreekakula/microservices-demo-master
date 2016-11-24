package io.pivotal.microservices.services;

import io.pivotal.microservices.services.pricing.PricingServer;
import io.pivotal.microservices.services.productcatalog.ProductCatalogServer;
import io.pivotal.microservices.services.registration.RegistrationServer;

/**
 * Allow the servers to be invoked from the command-line. The jar is built with
 * this as the <code>Main-Class</code> in the jar's <code>MANIFEST.MF</code>.
 * 
 * @author manikanta.s
 */
public class Main {

	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[1]);
			// Fall through into ..

		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		if (serverName.equals("registration") || serverName.equals("reg")) {
			RegistrationServer.main(args);
		} else if (serverName.equals("products-catalog")) {
			ProductCatalogServer.main(args);
		} else if (serverName.equals("pricing")) {
			PricingServer.main(args);
		} else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println("     where server-name is 'reg', 'registration', "
				+ "'products-catalog' or 'pricing' and server-port > 1024");
	}
}
