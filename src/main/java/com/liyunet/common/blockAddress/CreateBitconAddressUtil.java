package com.liyunet.common.blockAddress;

import java.math.BigInteger;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;


public class CreateBitconAddressUtil {

	public static String createAddress() {

		String string = RandomUtil.randomString("0123456789abcdef", 100);
		byte[] publicKey = new BigInteger(string, 16).toByteArray();
		byte[] sha256Bytes = Utils.sha256(publicKey);
//		System.out.println("sha256加密=" + Utils.bytesToHexString(sha256Bytes));
		RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(sha256Bytes, 0, sha256Bytes.length);
		byte[] ripemd160Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(ripemd160Bytes, 0);

//		System.out.println("ripemd160加密=" + Utils.bytesToHexString(ripemd160Bytes));

		byte[] networkID = new BigInteger("00", 16).toByteArray();
		byte[] extendedRipemd160Bytes = Utils.add(networkID, ripemd160Bytes);

//		System.out.println("添加NetworkID=" + Utils.bytesToHexString(extendedRipemd160Bytes));

		byte[] twiceSha256Bytes = Utils.sha256(Utils.sha256(extendedRipemd160Bytes));

//		System.out.println("两次sha256加密=" + Utils.bytesToHexString(twiceSha256Bytes));

		byte[] checksum = new byte[4];
		System.arraycopy(twiceSha256Bytes, 0, checksum, 0, 4);

//		System.out.println("checksum=" + Utils.bytesToHexString(checksum));

		byte[] binaryBitcoinAddressBytes = Utils.add(extendedRipemd160Bytes, checksum);

//		System.out.println("添加checksum之后=" + Utils.bytesToHexString(binaryBitcoinAddressBytes));

		String bitcoinAddress = Base58.encode(binaryBitcoinAddressBytes);
//		System.out.println("bitcoinAddress=" + bitcoinAddress);
		return bitcoinAddress;
	}
}
