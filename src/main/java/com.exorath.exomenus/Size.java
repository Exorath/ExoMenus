/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.exomenus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by toonsev on 3/18/2017.
 */
public enum Size {
    ONE_LINE(9),
    TWO_LINE(18),
    THREE_LINE(27),
    FOUR_LINE(36),
    FIVE_LINE(45),
    SIX_LINE(54);

    private final int slots;

    Size(int slots) {
        this.slots = slots;
    }

    /**
     * Fetches the amount of slots.
     *
     * @return The amount of slots.
     */
    public int getslots() {
        return slots;
    }

    public static Size getSize(int slots) {
        ArrayList<Size> sizes = new ArrayList(Arrays.asList(Size.values()));
        sizes.sort(new SizeComparator());
        for(Size size : sizes)
            if(slots <= size.getslots())
                return size;
        return null;
    }


    private static class SizeComparator implements Comparator<Size> {
        public int compare(Size o1, Size o2) {
            return o2.getslots() - o1.getslots();
        }
    }
}