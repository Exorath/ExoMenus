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

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by toonsev on 3/18/2017.
 */
public class InventoryMenu {
    private String title;
    private Size size;
    private MenuItem[] items;
    private InventoryMenu parent;
    private PublishSubject<InventoryClickEvent> clickSubject = PublishSubject.create();

    public InventoryMenu(String title, Size size, MenuItem[] items, InventoryMenu parent) {
        this.title = title;
        this.size = size;
        this.items = items;
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public Size getSize() {
        return size;
    }

    public MenuItem[] getItems() {
        return items;
    }

    public InventoryMenu getParent() {
        return parent;
    }

    public InventoryMenu setItem(int position, MenuItem item) {
        items[position] = item;
        return this;
    }

    public InventoryMenu fillEmpty(MenuItem item) {
        for (int i = 0; i < items.length; i++)
            if (items[i] == null)
                items[i] = item;
        return this;
    }

    public void onClick(InventoryClickEvent event) {
        clickSubject.onNext(event);
        int slot = event.getRawSlot();
        if (slot >= 0 && slot < size.getslots() && items[slot] != null) {
            items[slot].onItemClick(event);
        }
    }

    public Observable<InventoryClickEvent> getClickObservable() {
        return clickSubject;
    }

    public void open(Player player) {
        MenuPlayer menuPlayer = new MenuPlayer(Bukkit.createInventory(player, size.getslots()), this);
        Inventory inventory = Bukkit.createInventory(menuPlayer, size.getslots(), title);
        apply(inventory, player);
        player.openInventory(inventory);
    }

    public void update(Player player) {
        if (player.getOpenInventory() != null) {
            Inventory inventory = player.getOpenInventory().getTopInventory();
            if (inventory.getHolder() instanceof MenuPlayer) {
                if (((MenuPlayer) inventory.getHolder()).getMenu().equals(this)) {
                    apply(inventory, player);
                    player.updateInventory();
                }
            }
        }
    }

    private void apply(Inventory inventory, Player player) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                inventory.setItem(i, items[i].getItemStack(player));
            } else {
                inventory.setItem(i, null);
            }
        }
    }

    public void destroy(boolean andItems) {
        if (andItems)
            for (MenuItem menuItem : items)
                menuItem.destroy();
        title = null;
        size = null;
        items = null;
        parent = null;
        clickSubject.onComplete();
    }

}
