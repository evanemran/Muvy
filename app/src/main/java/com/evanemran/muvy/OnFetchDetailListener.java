package com.evanemran.muvy;

import com.evanemran.muvy.Models.DetailsResponse;

public interface OnFetchDetailListener {
    void onFetched(DetailsResponse response, String message);
    void onError(String message);
}
