package com.company.demo.web.person;

import com.company.demo.entity.Person;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.impl.DatasourceImplementation;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class PersonBrowse extends AbstractLookup {
    @Inject
    private CollectionDatasource<Person, UUID> personsDs;
    @Inject
    private Table<Person> personsTable;
    @Inject
    private Metadata metadata;

    @Override
    public void init(Map<String, Object> params) {
        personsDs.addItemPropertyChangeListener(e ->
                personsTable.repaint());


        personsTable.setIconProvider(new ListComponent.IconProvider<Person>() {
            @Nullable
            @Override
            public String getItemIcon(Person entity) {
                if (PersistenceHelper.isNew(entity)) {
                    return "font-icon:PLUS_SQUARE";
                } else if (isEdited(entity)) {
                    return "font-icon:SQUARE_O";
                }

                return "font-icon:CHECK_SQUARE";
            }
        });
    }

    private boolean isEdited(Person entity) {
        return ((DatasourceImplementation) personsDs).getItemsToUpdate().contains(entity);
    }

    public void addNew() {
        personsDs.addItem(metadata.create(Person.class));
    }

    public void saveAll() {
        personsDs.commit();
        personsTable.repaint();
    }
}