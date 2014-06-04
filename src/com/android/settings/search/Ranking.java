/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.search;

import com.android.settings.ChooseLockGeneric;
import com.android.settings.DataUsageSummary;
import com.android.settings.DateTimeSettings;
import com.android.settings.DevelopmentSettings;
import com.android.settings.DeviceInfoSettings;
import com.android.settings.DisplaySettings;
import com.android.settings.HomeSettings;
import com.android.settings.PrivacySettings;
import com.android.settings.SecuritySettings;
import com.android.settings.WallpaperTypeSettings;
import com.android.settings.WirelessSettings;
import com.android.settings.accessibility.AccessibilitySettings;
import com.android.settings.bluetooth.BluetoothSettings;
import com.android.settings.deviceinfo.Memory;
import com.android.settings.fuelgauge.PowerUsageSummary;
import com.android.settings.inputmethod.InputMethodAndLanguageSettings;
import com.android.settings.location.LocationSettings;
import com.android.settings.notification.NotificationDisplaySettings;
import com.android.settings.notification.NotificationSettings;
import com.android.settings.notification.OtherSoundSettings;
import com.android.settings.notification.ZenModeSettings;
import com.android.settings.print.PrintSettingsFragment;
import com.android.settings.users.UserSettings;
import com.android.settings.wifi.AdvancedWifiSettings;
import com.android.settings.wifi.WifiSettings;

import java.util.HashMap;

/**
 * Utility class for dealing with Search Ranking.
 */
public final class Ranking {

    public static final int RANK_WIFI = 1;
    public static final int RANK_BT = 2;
    public static final int RANK_DATA_USAGE = 3;
    public static final int RANK_WIRELESS = 4;
    public static final int RANK_HOME = 5;
    public static final int RANK_DISPLAY = 6;
    public static final int RANK_WALLPAPER = 7;
    public static final int RANK_NOTIFICATIONS = 8;
    public static final int RANK_MEMORY = 9;
    public static final int RANK_POWER_USAGE = 10;
    public static final int RANK_USERS = 11;
    public static final int RANK_LOCATION = 12;
    public static final int RANK_SECURITY = 13;
    public static final int RANK_IME = 14;
    public static final int RANK_PRIVACY = 15;
    public static final int RANK_DATE_TIME = 16;
    public static final int RANK_ACCESSIBILITY = 17;
    public static final int RANK_PRINTING = 18;
    public static final int RANK_DEVELOPEMENT = 19;
    public static final int RANK_DEVICE_INFO = 20;

    public static final int RANK_UNDEFINED = -1;
    public static final int RANK_OTHERS = 1024;
    public static final int BASE_RANK_DEFAULT = 2048;

    public static int sCurrentBaseRank = BASE_RANK_DEFAULT;

    private static HashMap<String, Integer> sRankMap = new HashMap<String, Integer>();
    private static HashMap<String, Integer> sBaseRankMap = new HashMap<String, Integer>();

    static {
        sRankMap.put(WifiSettings.class.getName(), RANK_WIFI);
        sRankMap.put(AdvancedWifiSettings.class.getName(), RANK_WIFI);

        sRankMap.put(BluetoothSettings.class.getName(), RANK_BT);

        sRankMap.put(DataUsageSummary.class.getName(), RANK_DATA_USAGE);

        sRankMap.put(WirelessSettings.class.getName(), RANK_WIRELESS);

        sRankMap.put(HomeSettings.class.getName(), RANK_HOME);

        sRankMap.put(DisplaySettings.class.getName(), RANK_DISPLAY);

        sRankMap.put(WallpaperTypeSettings.class.getName(), RANK_WALLPAPER);

        sRankMap.put(NotificationSettings.class.getName(), RANK_NOTIFICATIONS);
        sRankMap.put(NotificationDisplaySettings.class.getName(), RANK_NOTIFICATIONS);
        sRankMap.put(OtherSoundSettings.class.getName(), RANK_NOTIFICATIONS);
        sRankMap.put(ZenModeSettings.class.getName(), RANK_NOTIFICATIONS);

        sRankMap.put(Memory.class.getName(), RANK_MEMORY);

        sRankMap.put(PowerUsageSummary.class.getName(), RANK_POWER_USAGE);

        sRankMap.put(UserSettings.class.getName(), RANK_USERS);

        sRankMap.put(LocationSettings.class.getName(), RANK_LOCATION);

        sRankMap.put(SecuritySettings.class.getName(), RANK_SECURITY);
        sRankMap.put(ChooseLockGeneric.ChooseLockGenericFragment.class.getName(), RANK_SECURITY);

        sRankMap.put(InputMethodAndLanguageSettings.class.getName(), RANK_IME);

        sRankMap.put(PrivacySettings.class.getName(), RANK_PRIVACY);

        sRankMap.put(DateTimeSettings.class.getName(), RANK_DATE_TIME);

        sRankMap.put(AccessibilitySettings.class.getName(), RANK_ACCESSIBILITY);

        sRankMap.put(PrintSettingsFragment.class.getName(), RANK_PRINTING);

        sRankMap.put(DevelopmentSettings.class.getName(), RANK_DEVELOPEMENT);

        sRankMap.put(DeviceInfoSettings.class.getName(), RANK_DEVICE_INFO);

        sBaseRankMap.put("com.android.settings", 0);
    }

    public static int getRankForClassName(String className) {
        Integer rank = sRankMap.get(className);
        return (rank != null) ? (int) rank: RANK_OTHERS;
    }

    public static int getBaseRankForAuthority(String authority) {
        synchronized (sBaseRankMap) {
            Integer base = sBaseRankMap.get(authority);
            if (base != null) {
                return base;
            }
            sCurrentBaseRank++;
            sBaseRankMap.put(authority, sCurrentBaseRank);
            return sCurrentBaseRank;
        }
    }
}