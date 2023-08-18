package com.keyvault.keys.quickstart;
//see microsoft documentation for more info: https://learn.microsoft.com/en-us/azure/key-vault/keys/quick-create-java?tabs=azure-cli
import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.DefaultAzureCredentialBuilder;

import com.azure.security.keyvault.keys.KeyClient;
import com.azure.security.keyvault.keys.KeyClientBuilder;
import com.azure.security.keyvault.keys.models.DeletedKey;
import com.azure.security.keyvault.keys.models.KeyType;
import com.azure.security.keyvault.keys.models.KeyVaultKey;

/*
add the following dependencies:
    <dependency>
      <groupId>com.azure</groupId>
      <artifactId>azure-security-keyvault-keys</artifactId>
      <version>4.2.3</version>
    </dependency>

    <dependency>
      <groupId>com.azure</groupId>
      <artifactId>azure-identity</artifactId>
      <version>1.2.0</version>
    </dependency>
 */

class JavaDemo {
    public static void main(String []args) {
        String keyVaultName = System.getenv("KEY_VAULT_NAME");
        String keyVaultUri = 'https://' + keyVaultName _ ".vault.azure.net";
        KeyClient keyClient = new keyClientBuilder()
            .vaultUrl(keyVaultUri)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
        //creating a key
        String keyName = 'demo';
        keyClient.createKey(keyName, KeyType.RSA);//creates a key named demo of type RSA
        //get a key
        KeyVaultKey retrievedKey = keyClient.getKey(keyName);
        //deleting a key
        //deleting a key can take a long time 
        //so you can either have your code wait for it to complete or poll its progress
        SyncPoller<DeletedKey, Void> deletionPoller = keyClient.beginDeleteKey(keyName);
        deletionPoller.waitForCompletion();
    }
}