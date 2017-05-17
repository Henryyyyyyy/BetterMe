/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package me.henry.betterme.betterme.view.Recycler.version2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public abstract class AdapterDelegate<T> {

  /**
   * 重点：
   * 1.这个方法的作用就是，核实item的类型，就是用一个方法去判断，
   * 怎么样才算是这种类型的数据，如果是这种类型数据就返回true
   *
   * 2.这个是核心方法，recyclerView的adapter通过DelegateManager里面的getItemViewType判断每个item的类型，
   *而getItemViewType里面又会通过这个方法去判断，delegatelist里面是否含有这种类型的数据，如果有，就返回那种类型的delegate的Index,
   * index是唯一的
   * @param items 数据源
   * @param position The position in the datasource
   * @return 如果是true，那就是有效类型
   */
  public LayoutInflater mInflater;
  public AdapterDelegate(Context context) {
    mInflater = LayoutInflater.from(context);
  }
  protected abstract boolean isForViewType(@NonNull T items, int position);
  /**
   * 为数据源创建viewholder
   * @param parent  ViewGroup咯，在inflate获取布局的时候有用
   * @return The new instantiated {@link RecyclerView.ViewHolder}
   */
  @NonNull
  abstract protected BaseHolder onCreateViewHolder(ViewGroup parent);

  protected abstract void onBindViewHolder(T items, int position, BaseHolder holder,List<Object> payloads);

  protected abstract int getItemViewLayoutId();
}
