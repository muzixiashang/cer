package com.liyunet.common.sms;

public class SmsVoiceUploaderResult
{
  public int result;
  public String msg;
  public String file;
  
  public String toString()
  {
    if (this.result == 0) {
      return String.format(
        "SmsVoiceUploaderResult:result %d\nmsg %s\nfile %s", new Object[] { Integer.valueOf(this.result), this.msg, this.file });
    }
    return String.format("SmsVoiceUploaderResult:result %d\nmsg %s", new Object[] { Integer.valueOf(this.result), this.msg });
  }
}
