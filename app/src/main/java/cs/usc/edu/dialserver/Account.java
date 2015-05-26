package cs.usc.edu.dialserver;

/**
 * Created by zhouzhao on 5/25/15.
 */

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;
}
