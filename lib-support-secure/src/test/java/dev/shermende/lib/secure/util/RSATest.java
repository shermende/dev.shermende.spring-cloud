package dev.shermende.lib.secure.util;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class RSATest {
    private static final String MODULUS = "12688536196907054367414953488384816824591429780374055181201312716336965152946426891115225672259845173560221699794003328911091845408175525777176753678096513608513564333910923818878200235232759723930064857063496665866619241917177142344381333203011758728047336852661029729749255845934255327114325597388075606425540102193899770465576668634121103811668318575626822046611116751625912308766577848259630179271915002898550792752696550841399811304747507171284677822172404653390938426022295911899868226590358070394015711481131416391645681254273795800679412718304587095136846814552594696654070005601520822202189626628915223993305";
    private static final String PRIVATE_EXPONENT = "21561661439487064642758674689975257091120609161105983208711863289614843913932895458890205224230390570685203659589794284410149253831399886764820517691327072688079276732738356997376529385652225322872006133154520211343911252042576253735673488581579631077346910968259027287540565259807433592788953164000889647064121747025957578546457573361746624346568738381997536216829319016141804181257701656553451072386115456511541570980709775962065757424707265205242000765523803204984489636177404877399624085771148339642173220316461363820888146506830202329080178187667227405675190799707909560816505182284767707975548598497134833531491";
    private static final String PUBLIC_EXPONENT = "65537";

    @Test
    void getPrivateKey() throws IOException, GeneralSecurityException {
        final File file = getFile("jwt/private.key");
        final RSAPrivateKey key = RSA.getPrivateKey(file.getAbsolutePath());
        Assertions.assertEquals(new BigInteger(PRIVATE_EXPONENT), key.getModulus());
        Assertions.assertEquals(new BigInteger(MODULUS), key.getPrivateExponent());
    }

    @Test
    void getPrivateKeyFromString() throws IOException, GeneralSecurityException {
        final File file = getFile("jwt/private.key.txt");
        final RSAPrivateKey key = RSA.getPrivateKeyFromString(new String(Files.readAllBytes(file.toPath())));
        Assertions.assertEquals(new BigInteger(PRIVATE_EXPONENT), key.getModulus());
        Assertions.assertEquals(new BigInteger(MODULUS), key.getPrivateExponent());
    }

    @Test
    void getPublicKey() throws IOException, GeneralSecurityException {
        final File file = getFile("jwt/public.pem");
        final RSAPublicKey key = RSA.getPublicKey(file.getAbsolutePath());
        Assertions.assertEquals(new BigInteger(PRIVATE_EXPONENT), key.getModulus());
        Assertions.assertEquals(new BigInteger(PUBLIC_EXPONENT), key.getPublicExponent());
    }

    @Test
    void getPublicKeyFromString() throws IOException, GeneralSecurityException {
        final File file = getFile("jwt/public.pem.txt");
        final RSAPublicKey key = RSA.getPublicKeyFromString(new String(Files.readAllBytes(file.toPath())));
        Assertions.assertEquals(new BigInteger(PRIVATE_EXPONENT), key.getModulus());
        Assertions.assertEquals(new BigInteger(PUBLIC_EXPONENT), key.getPublicExponent());
    }

    @NotNull
    private File getFile(
        @NotNull String s
    ) {
        final ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(s)).getFile());
    }

}