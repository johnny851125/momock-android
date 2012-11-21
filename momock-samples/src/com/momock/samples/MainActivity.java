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
package com.momock.samples;

import android.os.Bundle;
import android.view.Menu;

import com.momock.app.CaseActivity;
import com.momock.app.ICase;
import com.momock.holder.FragmentContainerHolder;
import com.momock.samples.cases.mainmenu.MainMenuCase;

public class MainActivity extends CaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_main);
        
        getCase().getOutlet(Outlets.MAIN_CONTAINER).attach(new FragmentContainerHolder(this, R.id.fragment_content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getCase().getOutlet(Outlets.MAIN_MENU).attach(menu);
        return true;
    }

	@Override
	protected String getCaseName() {
		return Cases.MAIN;
	}

	@Override
	public void onBackPressed() {
        ICase mainmenu = getCase().getCase(MainMenuCase.class.getName());
		if (getCase().getOutlet(Outlets.MAIN_CONTAINER).getActivePlug() == mainmenu)
			super.onBackPressed();
		else
			mainmenu.run();
	}
}
