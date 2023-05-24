package com.easy.query.test;

import com.easy.query.test.BaseTest;
import com.easy.query.core.basic.plugin.track.TrackManager;
import com.easy.query.test.entity.SysUserTrack;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDateTime;

/**
 * create time 2023/4/7 22:30
 * 文件说明
 *
 * @author xuejiaming
 */
public class TrackTest extends BaseTest {

    @Test
    public void track1() {
        long l = easyQuery.deletable(SysUserTrack.class).whereById("1").executeRows();
        SysUserTrack sysUserTrack = new SysUserTrack();
        sysUserTrack.setId("1");
        sysUserTrack.setUsername("Username1");
        sysUserTrack.setPhone("13232323232");
        sysUserTrack.setIdCard("123456789000");
        sysUserTrack.setAddress("浙江省绍兴市越城区城市广场");
        sysUserTrack.setCreateTime(LocalDateTime.now());
        long l1 = easyQuery.insertable(sysUserTrack).executeRows();
        Assert.assertEquals(1, l1);
        TrackManager trackManager = easyQuery.getRuntimeContext().getTrackManager();
        try {
            trackManager.begin();
            SysUserTrack sysUserTrack1 = easyQuery.queryable(SysUserTrack.class).asTracking()
                    .whereById("1").firstOrNull();
            boolean b = easyQuery.addTracking(sysUserTrack1);
            Assert.assertFalse(b);
            sysUserTrack1.setPhone("9876543210");
            long l2 = easyQuery.updatable(sysUserTrack1).executeRows();
            Assert.assertEquals(1, l2);
            SysUserTrack sysUserTrack2 = easyQuery.queryable(SysUserTrack.class)
                    .whereById("1").firstOrNull();
            Assert.assertNotNull(sysUserTrack2);
            Assert.assertEquals("9876543210", sysUserTrack2.getPhone());
        } finally {
            trackManager.release();
        }
    }

    @Test
    public void track2() {

        long l = easyQuery.deletable(SysUserTrack.class).whereById("2").executeRows();

        SysUserTrack sysUserTrack = new SysUserTrack();
        sysUserTrack.setId("2");
        sysUserTrack.setUsername("Username1");
        sysUserTrack.setPhone("13232323232");
        sysUserTrack.setIdCard("123456789000");
        sysUserTrack.setAddress("浙江省绍兴市越城区城市广场");
        sysUserTrack.setCreateTime(LocalDateTime.now());
        long l1 = easyQuery.insertable(sysUserTrack).executeRows();
        Assert.assertEquals(1, l1);
        TrackManager trackManager = easyQuery.getRuntimeContext().getTrackManager();
        try {
            trackManager.begin();
            SysUserTrack sysUserTrack1 = easyQuery.queryable(SysUserTrack.class)
                    .whereById("2").firstOrNull();
            boolean b = easyQuery.addTracking(sysUserTrack1);
            Assert.assertTrue(b);
            sysUserTrack1.setPhone("9876543210");
            long l2 = easyQuery.updatable(sysUserTrack1).executeRows();
            Assert.assertEquals(1, l2);
            SysUserTrack sysUserTrack2 = easyQuery.queryable(SysUserTrack.class)
                    .whereById("2").firstOrNull();
            Assert.assertNotNull(sysUserTrack2);
            Assert.assertEquals("9876543210", sysUserTrack2.getPhone());
        } finally {
            trackManager.release();
        }
    }

    @Test
    public void track3() {

        long l = easyQuery.deletable(SysUserTrack.class).whereById("3").executeRows();

        SysUserTrack sysUserTrack = new SysUserTrack();
        sysUserTrack.setId("3");
        sysUserTrack.setUsername("Username1");
        sysUserTrack.setPhone("13232323232");
        sysUserTrack.setIdCard("123456789000");
        sysUserTrack.setAddress("浙江省绍兴市越城区城市广场");
        sysUserTrack.setCreateTime(LocalDateTime.now());
        long l1 = easyQuery.insertable(sysUserTrack).executeRows();
        Assert.assertEquals(1, l1);

        SysUserTrack sysUserTrack1 = easyQuery.queryable(SysUserTrack.class)
                .whereById("2").firstOrNull();
        boolean b = easyQuery.addTracking(sysUserTrack1);
        Assert.assertFalse(b);
    }

    @Test
    public void track4() {
        String id = "5";
        long l = easyQuery.deletable(SysUserTrack.class).whereById(id).executeRows();

        SysUserTrack sysUserTrack = new SysUserTrack();
        sysUserTrack.setId(id);
        sysUserTrack.setUsername("Username1");
        sysUserTrack.setPhone("13232323232");
        sysUserTrack.setIdCard("123456789000");
        sysUserTrack.setAddress("浙江省绍兴市越城区城市广场");
        sysUserTrack.setCreateTime(LocalDateTime.now());
        long l1 = easyQuery.insertable(sysUserTrack).executeRows();
        Assert.assertEquals(1, l1);
        TrackManager trackManager = easyQuery.getRuntimeContext().getTrackManager();
        try {
            trackManager.begin();
            SysUserTrack sysUserTrack1 = easyQuery.queryable(SysUserTrack.class).asTracking()
                    .whereById(id).firstOrNull();
            boolean b = easyQuery.addTracking(sysUserTrack1);
            Assert.assertFalse(b);
            long l2 = easyQuery.updatable(sysUserTrack1).executeRows();
            Assert.assertEquals(0, l2);

        } finally {
            trackManager.release();
        }
    }
    @Test
    public void track5() {
        String id = "6";
        long l = easyQuery.deletable(SysUserTrack.class).whereById(id).executeRows();

        SysUserTrack sysUserTrack = new SysUserTrack();
        sysUserTrack.setId(id);
        sysUserTrack.setUsername("Username1");
        sysUserTrack.setPhone("13232323232");
        sysUserTrack.setIdCard("123456789000");
        sysUserTrack.setAddress("浙江省绍兴市越城区城市广场");
        sysUserTrack.setCreateTime(LocalDateTime.now());
        long l1 = easyQuery.insertable(sysUserTrack).executeRows();
        Assert.assertEquals(1, l1);
        TrackManager trackManager = easyQuery.getRuntimeContext().getTrackManager();
        try {
            trackManager.begin();
            SysUserTrack sysUserTrack1 = easyQuery.queryable(SysUserTrack.class)
                    .whereById(id).firstOrNull();
            sysUserTrack1.setPhone("9876543210");
            long l2 = easyQuery.updatable(sysUserTrack1).executeRows();
            Assert.assertEquals(1, l2);
            SysUserTrack sysUserTrack2 = easyQuery.queryable(SysUserTrack.class)
                    .whereById(id).firstOrNull();
            Assert.assertNotNull(sysUserTrack2);
            Assert.assertEquals("9876543210", sysUserTrack2.getPhone());

        } finally {
            trackManager.release();
        }
    }
}
