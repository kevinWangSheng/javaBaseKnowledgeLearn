package VPN;


/**
 * @author wang
 * @create 2023-2023-02-0:44
 */
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;
public class GetVPN {
        public static void main(String []args) throws Exception {

            String cipherText = "mq4s/zhZQcd3VPf8qNKVJEjxKMP7dxCid+T3HHFXauuIQa7qXwS2KJMH5sfvVv1vMcBA/q7sJLzHFJff9fVpsgnuZ1IVJ4ZVndYg2+Wz6ebd3fpYXAaonAdVEG9yX3DmicCKAmU2I2Mu4LcJXXUsGEo7jHkcXd6qCXVSne0Vv+pH8PwOpnMS4XDTpJgwLyH3mxTiHSDUhm/OntogpmiR59axLhHg1WyhTgsVklEx/0gAJKUbQ9j9t2E3pT2+B+bNXoEVxfbvLHSqRtUbiQ3SexUlQxzSpmDuBgGG0cLoQX7bkPK+PeJ8s1f0xDzCkftKniGXOUbHUYF5nci3QcL4XzWixtUFnDHyHYPytFozLJ0= \n" +
                    "mq4s/zhZQcd3VPf8qNKVJJUDQ+I4xzt+lS+fWGACc5DhamcpyyAXo/RChgZNpcyQzk9y06NX++enVkORac+unbyQeI3iTBjoqtS6Zy46b5aK0AcBRFlzNXm24ir+CFJzEscqDpxahKnXLfP7083+x5s/6xQKUEjdLeVnMvOLeoU18O9zrhLsR5YsyAgQ0SOI26K5yxcWi1xgUFXIG/cA9pG1iJMDH9cpLosgZIzPyYc247xFL4RUCCz9jOTRIiC6iEDPSPm1W0wmcXlo50LC7CrLyJEclGkDVKwQ1bgO0psr/bnXE+aQzhVxKBYqQ78b+4hO+SejVsTtYTmW3c8lPFwwRcDmpy59F34QnIkvZxM= \n" +
                    "mq4s/zhZQcd3VPf8qNKVJOB4IIYy3n7YIYCXE37mpRc0FEpmU9ff5r+V3MPmKf9xGKeNj+6DnsQ6omE6QnU1A4FybMjKuchsgWbaKei9E1GRofiEsKbVLsVuVLT0gdGmaFDAC9M3QkB5elT+rcM5dvVcJ4VXPN7fNBSNloR6Uezpxx6cE03dGoAFUoniCrXHy0Z63YwHLEQW9gwLxVAuBY2uzWMhZXakvaiW7RiANTCIcabsU8yk7Yd6vZ+73yCXS7zU1Cc7o/jFBzC3IU5WzhjC1v8HtbRoA9HhH7l8zSwlqAXoujjllj3I5uSS7s8WEhVp3lATOAlvPzf/sglkO2P6DWZu82SNeHqsclCeJaA= \n" +
                    "mq4s/zhZQcd3VPf8qNKVJPbqLCU1hybl3hppphI1FZJSXnlyO7rf3lR+BJKrIHrW/clQfR3yvHlt/Vxe0gs0xB3j8dx/vzh/OYojPjFm74ioYSKvFRvXzOZI0hf9ujccBUEpbMIkPYmP32l8PNThxxoXd6Hyl8/rNbGWytxo8ucnsx0WIbR31oUyjEglqv6qbntt7PFKWFbMbsAb3dH3aXvk0vF3t7UqvpgjOrFT3GBSd9snUqnk2JIvciKAvr/4HjWKW0uQ8K8YPaLewk27VuAyIlwvejN4IhNUi642gHtmE2GZle9uCQUUhzVvTkn4wbckFHCa2QVtR7rN5nmtyg==";

            String key = "ks9KUrbWJj46AftX"; //heidong\leiting\mifeng KEY
            //key = "awdtif20190619ti"; //xuanfen KEY

            String[] strArray=cipherText.split(" ");
            for (String text : strArray) {
                System.out.println(decrypt(text,key));
            }
        }
        public static String decrypt(String str, String str2) throws Exception {
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            byte[] bytes = str2.getBytes(Charset.forName("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));
            instance.init(2, secretKeySpec, new IvParameterSpec(bytes2));
            byte[] doFinal = instance.doFinal(Base64.getDecoder().decode(str));
            return new String(doFinal, Charset.forName("UTF-8"));
        }



//    JAVA在线运行：https://www.tutorialspoint.com/compile_java_online.php
//
//    雷霆：https://www.lt71126.xyz:20000/api/evmess
//    黑洞：https://www.hd327658.xyz:20000/api/evmess
//    蜜蜂：https://www.09898434.xyz/api/evmess?deviceid=49c95313d64fb7c5unknown&apps=cd9186e318e291300db27867d958eae5
//
//    旋风：https://www.xfjyqirx.xyz:20000/api/evmess
//
//    path替换为：/path/120306182525
}
