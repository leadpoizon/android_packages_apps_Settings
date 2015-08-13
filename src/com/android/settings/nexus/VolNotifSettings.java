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
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

public class VolNotifSettings extends SettingsPreferenceFragment {

    private static final String KEY_HEADS_UP_SETTINGS = "heads_up_enabled";

    private Preference mHeadsUp;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.nexus_notif_settings);
		
        mHeadsUp = findPreference(KEY_HEADS_UP_SETTINGS);
    }

    private boolean getUserHeadsUpState() {
         return Settings.System.getInt(getContentResolver(),
                Settings.System.HEADS_UP_USER_ENABLED,
                Settings.System.HEADS_UP_USER_ON) != 0;
    }

    @Override
    public void onResume() {
        super.onResume();

        mHeadsUp.setSummary(getUserHeadsUpState()
                ? R.string.summary_heads_up_enabled : R.string.summary_heads_up_disabled);
    }

}
