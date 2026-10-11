/*
 * Copyright (C) 2016 The Android Open Source Project
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
package androidx.media3.extractor.mkv;

import static com.google.common.truth.Truth.assertThat;

import androidx.media3.common.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public final class MatroskaStereoAspectTest {
  @Test
  public void displayDimensionsDescribeOneEye() throws Exception {
    check(C.STEREO_MODE_MONO, 1200, 1080, 1f);
    check(C.STEREO_MODE_LEFT_RIGHT, 2400, 1080, 1f);
    check(C.STEREO_MODE_RIGHT_LEFT, 2400, 1080, 1f);
    check(C.STEREO_MODE_LEFT_RIGHT, 1200, 1080, 2f);
    check(C.STEREO_MODE_TOP_BOTTOM, 1200, 2160, 1f);
    check(C.STEREO_MODE_BOTTOM_TOP, 1200, 1080, .5f);
  }

  private static void check(int stereoMode, int width, int height, float ratio) throws Exception {
    MatroskaExtractor.Track track = new MatroskaExtractor.Track();
    track.codecId = "V_VP8";
    track.width = width;
    track.height = height;
    track.displayWidth = 1200;
    track.displayHeight = 1080;
    track.stereoMode = stereoMode;
    track.initializeFormat(1);
    assertThat(track.format.pixelWidthHeightRatio).isEqualTo(ratio);
    assertThat(track.format.stereoMode).isEqualTo(stereoMode);
    assertThat(track.format.width).isEqualTo(width);
    assertThat(track.format.height).isEqualTo(height);
  }
}
