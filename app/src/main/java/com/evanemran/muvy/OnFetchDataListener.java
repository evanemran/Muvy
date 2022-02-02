package com.evanemran.muvy;

import com.evanemran.muvy.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetched(APIResponse response, String message);
    void onError(String message);
}
