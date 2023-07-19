import os
from azure.keyvault.keys import KeyClient
from azure.identity import DefaultAzureCredential

#make sure your local environment is configured to work with azure key vault
    #https://learn.microsoft.com/en-us/azure/developer/python/configure-local-development-environment?tabs=windows%2Capt%2Ccmd#use-python-virtual-environments

#see this documentation for even more info: https://learn.microsoft.com/en-us/azure/key-vault/keys/quick-create-python?tabs=azure-cli
#install packages using: 
#pip install azure-identity
#pip install azure-keyvault-keys

#make an environment variable for the KEY_VAULT_NAME
    #export KEY_VAULT_NAME="vault-name"

#get vault name and connect
keyVaultName = os.environ["KEY_VAULT_NAME"]
KVUri = "https://" + keyVaultName + ".vault.azure.net"

credential = DefaultAzureCredential()
client = KeyClient(vault_url=KVUri, credential=credential)

#example key
keyName = "sampleKey"

#get key
rsa_key = client.create_rsa_key(keyName, size=2048)

retrieved_key = client.get_key(keyName)

#delete key
poller = client.begin_delete_key(keyName)
deleted_key = poller.result()
