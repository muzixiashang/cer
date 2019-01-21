package com.liyunet.test;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.liyunet.common.password.MD5;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.util.DemandNumFactory;

public class Sign {
@SuppressWarnings("deprecation")
public static void main(String[] args) {
//	1547692877609
//	f5b37eb31bd51dbbe878c8ed9d6ac0e4
//	System.err.println(System.currentTimeMillis());
//	String md5Java = MD5.md5Java("U#Hr$1rLrL3dz9LlKxaM1547692877609");
//	System.err.println(md5Java);
//	String a=URLEncoder.encode("fw7U4eH+zicepSCdwblGFw==");
//	System.out.println(a);
	int orderId = DemandNumFactory.getInstance().getDemandNum();
	System.out.println(orderId);
}

}
