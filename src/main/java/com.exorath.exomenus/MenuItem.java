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
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by toonsev on 3/18/2017.
 */
public class MenuItem {
    private final String title;
    private final ItemStack itemStack;
    private final List<String> lore;
    private final PublishSubject<InventoryClickEvent> clickEventPublishSubject = PublishSubject.create();
    public MenuItem(String title, ItemStack itemStack, String... lore) {
        this.title = title;
        this.itemStack = itemStack;
        this.lore = Arrays.asList(lore);
    }

    public String getTitle() {
        return title;
    }


    public List<String> getLore() {
        return lore;
    }

    public void onItemClick(InventoryClickEvent event) {
        clickEventPublishSubject.onNext(event);
    }
    public Observable<InventoryClickEvent> getClickObservable(){
        return clickEventPublishSubject;
    }
    public ItemStack getItemStack(Player player){
        ItemStack is = itemStack.clone();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(title);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

    public void destroy(){
        clickEventPublishSubject.onComplete();
    }
}
