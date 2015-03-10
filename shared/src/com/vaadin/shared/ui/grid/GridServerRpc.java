/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.shared.ui.grid;

import java.util.List;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;
import com.vaadin.shared.data.sort.SortDirection;

/**
 * Client-to-server RPC interface for the Grid component
 * 
 * @since 7.4
 * @author Vaadin Ltd
 */
public interface GridServerRpc extends ServerRpc {

    void select(List<String> newSelection);

    void selectAll();

    void sort(String[] columnIds, SortDirection[] directions,
            boolean userOriginated);

    /**
     * Informs the server that an item has been clicked in Grid.
     * 
     * @param rowKey
     *            a key identifying the clicked item
     * @param columnId
     *            column id identifying the clicked property
     * @param details
     *            mouse event details
     */
    void itemClick(String rowKey, String columnId, MouseEventDetails details);

    /**
     * This is a trigger for Grid to send whatever has changed regarding the
     * details components.
     * <p>
     * The components can't be sent eagerly, since they are generated as a side
     * effect in
     * {@link com.vaadin.data.RpcDataProviderExtension#beforeClientResponse(boolean)}
     * , and that is too late to change the hierarchy. So we need this
     * round-trip to work around that limitation.
     * 
     * @since
     * @param fetchId
     *            an unique identifier for the request
     * @see com.vaadin.ui.Grid#setDetailsVisible(Object, boolean)
     */
    void sendDetailsComponents(int fetchId);
}
