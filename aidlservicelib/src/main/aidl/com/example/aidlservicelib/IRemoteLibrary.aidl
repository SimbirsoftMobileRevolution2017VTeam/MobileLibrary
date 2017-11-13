// IRemoteLibrary.aidl
package com.example.aidlservicelib;

import com.example.aidlservicelib.IResultHandler;

interface IRemoteLibrary {
    oneway void loadLibrary(IResultHandler callBack);
}
