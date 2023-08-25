// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2020, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
// end::copyright[]
package svt.mongo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import javax.net.ssl.SSLContext;

import com.ibm.websphere.ssl.JSSEHelper;
import com.ibm.websphere.ssl.SSLException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.websphere.crypto.PasswordUtil;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Collections;

@ApplicationScoped
public class MongoProducer {

    // tag::mongoProducerInjections[]
    @Inject
    @ConfigProperty(name = "mongo.hostname", defaultValue = "YOURMONGODB.fyre.ibm.com")
    String hostname;

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.dbname", defaultValue = "crewdb")
    String dbName;

/*    
    @Inject
    @ConfigProperty(name = "mongo.user")
    String user;

    @Inject
    @ConfigProperty(name = "mongo.pass.encoded")
    String encodedPass;
*/
    
    
    @Produces
    public MongoClient createMongo() throws SSLException {
/*    	
        String password = PasswordUtil.passwordDecode(encodedPass);
        MongoCredential creds = MongoCredential.createCredential(
                user,
                dbName,
                password.toCharArray()
        );

        SSLContext sslContext = JSSEHelper.getInstance().getSSLContext(
                "outboundSSLContext",
                Collections.emptyMap(),
                null
        );

        return new MongoClient(
                new ServerAddress(hostname, port), creds, new MongoClientOptions.Builder().sslEnabled(true).sslContext(sslContext).build()
*/
    	//MongoClient mongoClient = MongoClients.create(new ConnectionString("mongodb://avl-mongo-1.fyre.ibm.com:27017"));
        return new MongoClient(new ServerAddress(hostname, port)); 		
    }
    
    @Produces
    public MongoDatabase createDB(MongoClient client) {
        return client.getDatabase(dbName);
    }

    public void close(@Disposes MongoClient toClose) {
        toClose.close();
     }
}
