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

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by toonsev on 3/18/2017.
 */
public class MenuPlayer implements InventoryHolder {
    private Inventory iventory;
    private InventoryMenu menu;

    public MenuPlayer(Inventory iventory, InventoryMenu menu) {
        this.iventory = iventory;
        this.menu = menu;
    }

    public Inventory getInventory() {
        return getInventory();
    }

    public InventoryMenu getMenu() {
        return menu;
    }
}
