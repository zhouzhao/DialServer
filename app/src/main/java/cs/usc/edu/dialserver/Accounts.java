package cs.usc.edu.dialserver;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhouz on 5/26/15.
 */
public class Accounts {

    @SerializedName("accounts")
    public List<Account> accounts;
}
