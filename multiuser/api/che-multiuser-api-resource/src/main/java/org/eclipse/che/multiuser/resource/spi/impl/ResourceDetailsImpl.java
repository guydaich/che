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
package org.eclipse.che.multiuser.resource.spi.impl;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.che.multiuser.resource.model.ResourceDetails;
import org.eclipse.che.multiuser.resource.model.ProvidedResources;
import org.eclipse.che.multiuser.resource.model.Resource;

/** @author Sergii Leschenko */
public class ResourceDetailsImpl implements ResourceDetails {
  private String accountId;
  private List<ProvidedResourcesImpl> providedResources;
  private List<ResourceImpl> totalResources;

  public ResourceDetailsImpl(ResourceDetails license) {
    this(license.getAccountId(), license.getProvidedResources(), license.getTotalResources());
  }

  public ResourceDetailsImpl(
      String owner,
      List<? extends ProvidedResources> providedResources,
      List<? extends Resource> totalResources) {
    this.accountId = owner;
    if (providedResources != null) {
      this.providedResources =
          providedResources.stream().map(ProvidedResourcesImpl::new).collect(Collectors.toList());
    }
    if (totalResources != null) {
      this.totalResources =
          totalResources.stream().map(ResourceImpl::new).collect(Collectors.toList());
    }
  }

  @Override
  public String getAccountId() {
    return accountId;
  }

  @Override
  public List<ProvidedResourcesImpl> getProvidedResources() {
    if (providedResources == null) {
      providedResources = new ArrayList<>();
    }
    return providedResources;
  }

  @Override
  public List<ResourceImpl> getTotalResources() {
    if (totalResources == null) {
      totalResources = new ArrayList<>();
    }
    return totalResources;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ResourceDetailsImpl)) return false;
    ResourceDetailsImpl license = (ResourceDetailsImpl) o;
    return Objects.equal(accountId, license.accountId)
        && Objects.equal(providedResources, license.providedResources)
        && Objects.equal(totalResources, license.totalResources);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(accountId, providedResources, totalResources);
  }

  @Override
  public String toString() {
    return "ResourceDetailsImpl{"
        + "accountId='"
        + accountId
        + '\''
        + ", providedResources="
        + providedResources
        + ", totalResources="
        + totalResources
        + '}';
  }
}
