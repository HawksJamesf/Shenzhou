package com.hawksjamesf.simpleweather.event;

import com.hawksjamesf.simpleweather.bean.RealTimeBean;
import com.hawksjamesf.simpleweather.bean.fifteendaysbean.SkyConBean;
import com.hawksjamesf.simpleweather.bean.fifteendaysbean.TempeBean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Copyright ® $ 2017
 * All right reserved.
 * Code Link : https://github.com/HawksJamesf/SimpleWeather
 *  @author: hawks jamesf
 *  @since: 2017/7/4
 */

public class RealtimeEvent {

    @Inject
    public RealtimeEvent() {
    }

    @Override
    public String toString() {
        return "RealtimeEvent{" +
                "fifteenDatas=" + fifteenDatas +
                ", valueReturnEvent=" + valueReturnEvent +
                ", rtBean=" + rtBean +
                '}';
    }

    private Map<List<TempeBean>, List<SkyConBean>> fifteenDatas;
    private int valueReturnEvent;
    private RealTimeBean rtBean;



    public Map<List<TempeBean>, List<SkyConBean>> getMapWithFifteen() {
        return fifteenDatas;
    }

    public int getValueReturnEvent() {
        return valueReturnEvent;
    }

    public RealtimeEvent setValueReturnEvent(int valueReturnEvent) {
        this.valueReturnEvent = valueReturnEvent;
        return this;
    }

    public RealtimeEvent setVauleWithRealTime(RealTimeBean bean) {
        this.rtBean = bean;
        return this;
    }

    public RealTimeBean getVauleWithRealTime() {
        return rtBean;
    }

}