/*
 * Copyright (C) 2012 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.swift.service;

import com.google.common.net.HostAndPort;
import io.airlift.configuration.Config;
import io.airlift.units.DataSize;
import io.airlift.units.DataSize.Unit;
import io.airlift.units.Duration;
import io.airlift.units.MaxDataSize;
import io.airlift.units.MinDuration;

import javax.validation.constraints.NotNull;

import java.util.concurrent.TimeUnit;

public class ThriftClientConfig
{
    public static final Duration DEFAULT_CONNECT_TIMEOUT = new Duration(500, TimeUnit.MILLISECONDS);
    public static final Duration DEFAULT_RECEIVE_TIMEOUT = new Duration(1, TimeUnit.MINUTES);
    public static final Duration DEFAULT_READ_TIMEOUT = new Duration(10, TimeUnit.SECONDS);
    public static final Duration DEFAULT_WRITE_TIMEOUT = new Duration(1, TimeUnit.MINUTES);
    public static final DataSize DEFAULT_MAX_FRAME_SIZE = new DataSize(16, Unit.MEGABYTE);

    private DataSize maxFrameSize = DEFAULT_MAX_FRAME_SIZE;
    private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private Duration receiveTimeout = DEFAULT_RECEIVE_TIMEOUT;
    private Duration readTimeout = DEFAULT_READ_TIMEOUT;
    private Duration writeTimeout = DEFAULT_WRITE_TIMEOUT;
    private HostAndPort socksProxy;

    @MinDuration("1ms")
    public Duration getConnectTimeout()
    {
        return connectTimeout;
    }

    @Config("thrift.client.connect-timeout")
    public ThriftClientConfig setConnectTimeout(Duration connectTimeout)
    {
        this.connectTimeout = connectTimeout;
        return this;
    }

    @MinDuration("1ms")
    public Duration getReceiveTimeout()
    {
        return receiveTimeout;
    }

    @Config("thrift.client.receive-timeout")
    public ThriftClientConfig setReceiveTimeout(Duration receiveTimeout)
    {
        this.receiveTimeout = receiveTimeout;
        return this;
    }

    @MinDuration("1ms")
    public Duration getReadTimeout()
    {
        return readTimeout;
    }

    @Config("thrift.client.read-timeout")
    public ThriftClientConfig setReadTimeout(Duration readTimeout)
    {
        this.readTimeout = readTimeout;
        return this;
    }

    @MinDuration("1ms")
    public Duration getWriteTimeout()
    {
        return writeTimeout;
    }

    @Config("thrift.client.write-timeout")
    public ThriftClientConfig setWriteTimeout(Duration writeTimeout)
    {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public HostAndPort getSocksProxy()
    {
        return socksProxy;
    }

    @Config("thrift.client.socks-proxy")
    public ThriftClientConfig setSocksProxy(HostAndPort socksProxy)
    {
        this.socksProxy = socksProxy;
        return this;
    }

    @NotNull
    @MaxDataSize("1GB")
    public DataSize getMaxFrameSize()
    {
        return maxFrameSize;
    }

    @Config("thrift.client.max-frame-size")
    public ThriftClientConfig setMaxFrameSize(DataSize maxFrameSize)
    {
        this.maxFrameSize = maxFrameSize;
        return this;
    }
}
