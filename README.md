# Azure-Key-Vault-Demo-Code
some demo code showing how to use azure key vault within your code in various languages

# Universal Guide
In general to access keys from Azure Key Vault you need to:
- import the language specific library for interfacing with Azure Key Vault
- read in the key vault name from an evironment variable
- then make a url by appending "https://", the vault name, and ".vault.azure.net"
- get a default credential to access Azure Key Vault from a library function
- then connect to Azure Key Vault using that url and credential and a library function
- now you can get, add, remove keys with given functions as you wish based on the documentation for your specific language
