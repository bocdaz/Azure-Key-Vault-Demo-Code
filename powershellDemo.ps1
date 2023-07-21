// refer : https://learn.microsoft.com/en-us/azure/key-vault/secrets/quick-create-powershell
// Create Key vault:
New-AzKeyVault -Name "<your-unique-keyvault-name>" -ResourceGroupName "myResourceGroup" -Location "EastUS"

Vault Name: The name you provided to the -Name parameter above.
Vault URI: In the example, this is https://<your-unique-keyvault-name>.vault.azure.net/. Applications that use your vault through its REST API must use this URI.

//user accessibility :
Set-AzKeyVaultAccessPolicy -VaultName "<your-unique-keyvault-name>" -UserPrincipalName "user@domain.com" -PermissionsToSecrets get,set,delete

// Add Secret
$secretvalue = ConvertTo-SecureString "hVFkk965BuUv" -AsPlainText -Force
$secret = Set-AzKeyVaultSecret -VaultName "<your-unique-keyvault-name>" -Name "ExamplePassword" -SecretValue $secretvalue

// Retrive Secret
$secret = Get-AzKeyVaultSecret -VaultName "<your-unique-keyvault-name>" -Name "ExamplePassword" -AsPlainText

// Clean up resource
Remove-AzResourceGroup -Name myResourceGroup
