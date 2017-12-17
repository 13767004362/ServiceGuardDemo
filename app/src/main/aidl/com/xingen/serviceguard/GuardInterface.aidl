// GuardInterface.aidl
package com.xingen.serviceguard;

// Declare any non-default types here with import statements

interface GuardInterface {
    /**
     *获取被守护的服务名
     */
    String getGuardServiceName();
}
