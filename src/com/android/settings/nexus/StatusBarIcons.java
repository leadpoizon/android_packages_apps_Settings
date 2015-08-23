/*
 * Copyright (C) 2015 The Pure Nexus Project
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

package com.android.settings.nexus;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class StatusBarIcons extends SettingsPreferenceFragment
        implements OnPreferenceChangeListener {

    private static final String STATUS_BAR_WIFI_COLOR  = "status_bar_wifi_color";
    private static final String STATUS_BAR_NETWORK_COLOR  = "status_bar_network_color";
    private static final String STATUS_BAR_AIRPLANE_COLOR = "status_bar_airplane_color";

    private static final String COLOR_ICONS  = "coloricons";

    private ColorPickerPreference mWifiColor;
    private ColorPickerPreference mNetworkColor;
    private ColorPickerPreference mAirplaneColor;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.nexus_statusbar_icons);
        ContentResolver resolver = getActivity().getContentResolver();
        PreferenceScreen prefSet = getPreferenceScreen();

        final PreferenceCategory iconcolors =
                (PreferenceCategory) prefSet.findPreference(COLOR_ICONS);

        mWifiColor = (ColorPickerPreference) findPreference(STATUS_BAR_WIFI_COLOR);
        mWifiColor.setOnPreferenceChangeListener(this);
        mWifiColor.setSummary(mWifiColor.getSummaryText() + ColorPickerPreference.convertToARGB(Settings.System.getInt(resolver,
                     Settings.System.STATUS_BAR_WIFI_COLOR, mWifiColor.getPrefDefault())));
        mWifiColor.setNewPreviewColor(Settings.System.getInt(resolver, Settings.System.STATUS_BAR_WIFI_COLOR, mWifiColor.getPrefDefault()));

        mNetworkColor = (ColorPickerPreference) findPreference(STATUS_BAR_NETWORK_COLOR);
        mNetworkColor.setOnPreferenceChangeListener(this);
        mNetworkColor.setSummary(mNetworkColor.getSummaryText() + ColorPickerPreference.convertToARGB(Settings.System.getInt(resolver,
                     Settings.System.STATUS_BAR_NETWORK_COLOR, mNetworkColor.getPrefDefault())));
        mNetworkColor.setNewPreviewColor(Settings.System.getInt(resolver, Settings.System.STATUS_BAR_NETWORK_COLOR, mNetworkColor.getPrefDefault()));

        mAirplaneColor = (ColorPickerPreference) findPreference(STATUS_BAR_AIRPLANE_COLOR);
        mAirplaneColor.setOnPreferenceChangeListener(this);
        mAirplaneColor.setSummary(mAirplaneColor.getSummaryText() + ColorPickerPreference.convertToARGB(Settings.System.getInt(resolver,
                     Settings.System.STATUS_BAR_AIRPLANE_COLOR, mAirplaneColor.getPrefDefault())));
        mAirplaneColor.setNewPreviewColor(Settings.System.getInt(resolver, Settings.System.STATUS_BAR_AIRPLANE_COLOR, mAirplaneColor.getPrefDefault()));

         if (Utils.isWifiOnly(getActivity())) {
             iconcolors.removePreference(findPreference(Settings.System.STATUS_BAR_NETWORK_COLOR));
         }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mWifiColor) {
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_WIFI_COLOR, (Integer) newValue);
            preference.setSummary(((ColorPickerPreference) preference).getSummaryText() + ColorPickerPreference.convertToARGB((Integer) newValue));
            return true;
        } else if (preference == mNetworkColor) {
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_NETWORK_COLOR, (Integer) newValue);
            preference.setSummary(((ColorPickerPreference) preference).getSummaryText() + ColorPickerPreference.convertToARGB((Integer) newValue));
            return true;
        } else if (preference == mAirplaneColor) {
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_AIRPLANE_COLOR, (Integer) newValue);
            preference.setSummary(((ColorPickerPreference) preference).getSummaryText() + ColorPickerPreference.convertToARGB((Integer) newValue));
            return true;
        }
        return false;
    }
}
