/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.multiuser.resource.shared.dto;

import java.util.List;
import org.eclipse.che.dto.shared.DTO;
import org.eclipse.che.multiuser.resource.model.FreeResourcesLimit;

/** @author Sergii Leschenko */
@DTO
public interface FreeResourcesLimitDto extends FreeResourcesLimit {
  @Override
  String getAccountId();

  void setAccountId(String accountId);

  FreeResourcesLimitDto withAccountId(String accountId);

  @Override
  List<ResourceDto> getResources();

  void setResources(List<ResourceDto> resources);

  FreeResourcesLimitDto withResources(List<ResourceDto> resources);
}
