/*******************************************************************************
 * Copyright 2012 momock.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.momock.samples.cases.tab;

import android.support.v4.app.Fragment;

import com.momock.app.Case;
import com.momock.app.ICase;
import com.momock.event.IEventArgs;
import com.momock.event.IEventHandler;
import com.momock.holder.FragmentHolder;
import com.momock.holder.TabHolder;
import com.momock.holder.TextHolder;
import com.momock.holder.ViewHolder;
import com.momock.outlet.IOutlet;
import com.momock.outlet.action.ActionPlug;
import com.momock.outlet.card.CardPlug;
import com.momock.outlet.card.ICardPlug;
import com.momock.outlet.tab.TabOutlet;
import com.momock.outlet.tab.TabPlug;
import com.momock.samples.Outlets;
import com.momock.samples.R;

public class TabCase extends Case<Fragment>{

	public TabCase(ICase<?> parent) {
		super(parent);
	}

	TabOutlet tabs = new TabOutlet();
	ICardPlug plug = CardPlug.get(FragmentHolder.get(R.layout.case_tab, this));
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate() {	
		IOutlet outlet = getParent().getOutlet(Outlets.SAMPLES);
		outlet.addPlug(ActionPlug.get(TextHolder.get("Tab Sample")).addExecuteEventHandler(new IEventHandler<IEventArgs>(){
			@Override
			public void process(Object sender, IEventArgs args) {
				run();
			}			
		}));

		tabs.addPlug(TabPlug.get(TextHolder.get("Tab 1"), null, ViewHolder.get(R.layout.tab_one)));
		tabs.addPlug(TabPlug.get(TextHolder.get("Tab 2"), null, ViewHolder.get(R.layout.tab_two)));
		tabs.addPlug(TabPlug.get(TextHolder.get("Tab 3"), null, ViewHolder.get(R.layout.tab_three)));
		tabs.addPlug(TabPlug.get(TextHolder.get("Tab 4"), null, ViewHolder.get(R.layout.tab_four)));
	}

	@Override
	public void run(Object... args) {
		getParent().getOutlet(Outlets.MAIN_CONTAINER).setActivePlug(plug);
	}

	@Override
	public void onAttach(Fragment target) {
		tabs.attach(TabHolder.get(target));
	}

}
