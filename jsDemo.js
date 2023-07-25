
//see microsoft documentation for more info: https://learn.microsoft.com/en-us/azure/key-vault/keys/quick-create-node?tabs=windows
//install libraries
//npm install @azure/keyvault-keys
//npm install @azure/identity

const { KeyClient} = require("@azure/keyvault-keys");
const { DefaultAzureCredential } = require("@azure/identity");

async function main() {
    const credential = new DefaultAzureCredential();

    const keyVaultName = process.env['KEY_VAULT_NAME'];
    if (!keyVaultName) throw new Error("KEY_VAULT_NAME is empty");
    const url = "htts://" + keyVaultName + ".vault.azure.net";

    const client = new KeyClient(url, credential);

    //create a key
    const result = await client.createKey(keyName, "EC");

    //get a key
    const key = await client.getKey(keyName);

    //delete a key
    const deletePoller = await client.beginDeleteKey(keyName);
    //purge a deleted key, this may take some time
    await client.purgeDeletedKey(keyName);
}