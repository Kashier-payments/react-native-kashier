package kashier.wrapper;

import com.facebook.react.bridge.ReadableMap;

import io.kashier.sdk.Core.model.Request.Payment.Connected_Accounts;

public class KashierConnectedAccountParser {
    static Connected_Accounts connectedAccountParser (ReadableMap connectedAccountData) {
        Connected_Accounts _connectedAccountData;

        String mid = "";

        if (connectedAccountData.hasKey("mid") && connectedAccountData.getString("mid") != null) {
            mid = connectedAccountData.getString("mid");
        }
        _connectedAccountData = new Connected_Accounts(mid);

        return _connectedAccountData;
    }
}
