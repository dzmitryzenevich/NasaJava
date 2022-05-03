package com.dzenlab.nasajava.di.component;

import com.dzenlab.nasajava.di.module.DomainModule;
import com.dzenlab.nasajava.di.module.PresentationModule;
import com.dzenlab.nasajava.presentation.fragment.MainFragment;
import dagger.Subcomponent;

@Subcomponent(modules = {
        DomainModule.class,
        PresentationModule.class})
public interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {

        FragmentComponent create();
    }

    void inject(MainFragment mainFragment);
}
