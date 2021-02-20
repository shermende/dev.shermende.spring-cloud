## Create RSA key-pair for JWT

```
create keystore
$ keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password
extract public key
$ keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
```