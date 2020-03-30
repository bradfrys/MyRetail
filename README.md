## Local Cassandra Container
Setup for testing with a local docker Cassandra instance

The following steps will likely work with the most recent version of the DSE Cassandra Docker image, but a version matching the application's DSE libraries is preferable. These instructions can easily be adapted to create DSE containers for other applications; use the same container configurations and change the DSE configuration as needed.

### Dependencies
* Docker installed (cli or desktop application, Kitematic optional but highly recommended)
* DSE Cassandra Docker image downloaded (```docker pull datastax/dse-server``` from the cli, or search for ```dse-server``` within Kitematic [Docker Hub](https://hub.docker.com/r/datastax/dse-server "datastax/dse-server").

### Configure Docker container
The following properties need to be mapped on your container. These can be added to your run command or saved with the container using Kitematic.
* Docker port -> published IP:port
* `561 -> localhost:561`
* `7000 -> localhost:7000`
* `7001 -> localhost:7001`
* `7199 -> localhost:7199`
* `9042 -> localhost:9042`
* `9160 -> localhost:9160`


* environment variable -> value
* `PATH = /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin`
* `GOSU_VERSION = 1.10`
* `GPG_KEYS = 514A2AD631A57A16DD0047EC749D6EEC0353B12C 	A26E528B271F19B9E5D8E19EA278B781FE4B2BDA`
* `CASSANDRA_VERSION = 3.11.5`
* `CASSANDRA_CONFIG = /etc/cassandra`
* `CASSANDRA_LISTEN_ADDRESS = 172.17.0.2`
* `CASSANDRA_BROADCAST_ADDRESS = 172.17.0.2`

Properties for this service are set in application.yml. __The correct contact point depends on your local network IP address.__ See comments for specifics.

### Creating table

Start a new DSE Cassandra container using this command: ```docker run -e DS_LICENSE=accept --name your-container-name -d datastax/dse-server```, or press start in Kitematic. Note: Allow gossip to settle before attempting to establish a connection.

Managing the database within the container can be accomplished in any standard manner (ie DevCenter). Whichever tool you use, it should to be pointed to 127.0.0.1 (using 'localhost' or your local network IP address is unreliable due to different local networking/proxy setups).

The following CQL queries can be run on the new container to create a keyspace and table compatible with this application.

```genericsql
CREATE KEYSPACE IF NOT EXISTS me_retail
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };
```

```genericsql
CREATE TABLE IF NOT EXISTS my_retail.price_table (
 "product_id" text,
 "value" double,
 "currency_code" text,
 PRIMARY KEY ("product_id")
);
```

###Verify and start application
You can confirm the table was created correctly with the following insert and select CQL statements.
```genericsql
INSERT INTO my_retail.price_table("product_id", "value", "currency_code") VALUES ('13860428', 13.49, 'USD');
SELECT * FROM my_retail.price_table;
```

Run this application with the default configuration to use the Cassandra Container.
