/**
 * Copyright 2005-2012 Restlet S.A.S.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL
 * 1.0 (the "Licenses"). You can select the license that you prefer but you may
 * not use this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package cs.usc.edu.dialserver;

import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.restlet.ext.gson.GsonConverter;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

/**
 * Implementation of the resource containing the list of mail accounts.
 */
public class AccountsServerResource extends ServerResource implements
        AccountsResource {

    private static final String TAG = "AccountsServerResource";

    /** Static list of accounts stored in memory. */
    private static final List<Account> accounts = new CopyOnWriteArrayList<Account>();

    /**
     * Returns the static list of accounts stored in memory.
     * 
     * @return The static list of accounts.
     */
    public static List<Account> getAccounts() {
        return accounts;
    }

    public Representation represent() {
//        return new JacksonRepresentation<List<Account>>(accounts);
        return null;
    }

    public String add(Representation representation) {

        GsonConverter converter = new GsonConverter();
        Account account = null;
        String result = "-1";

        try {
            account = converter.toObject(representation, Account.class, null);
        } catch (Exception exp) {
            Log.e(TAG, exp.getMessage());
        }

        if (null != account) {
            getAccounts().add(account);
            result = Integer.toString(getAccounts().indexOf(account));
        }

        return result;
    }
}