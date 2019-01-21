package com.liyunet.common.sms;

public class SmsSingleVoiceSenderResult
{
  public int result;
  public String errmsg;
  public String ext = "";
  public String callid;
  
  public String toString()
  {
    if (this.result == 0) {
      return String.format(
        "SmsSingleVoiceSenderResult\nresult %d\nerrmsg %s\next %s\ncallid %s", new Object[] {
        Integer.valueOf(this.result), this.errmsg, this.ext, this.callid });
    }
    return String.format(
      "SmsSingleVoiceSenderResult\nresult %d\nerrmsg %s\next %s", new Object[] {
      Integer.valueOf(this.result), this.errmsg, this.ext });
  }
}
