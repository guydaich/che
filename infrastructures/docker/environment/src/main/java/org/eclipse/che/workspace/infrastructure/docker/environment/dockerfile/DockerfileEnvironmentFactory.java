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
package org.eclipse.che.workspace.infrastructure.docker.environment.dockerfile;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.eclipse.che.api.core.ValidationException;
import org.eclipse.che.api.core.model.workspace.Warning;
import org.eclipse.che.api.installer.server.InstallerRegistry;
import org.eclipse.che.api.workspace.server.spi.InfrastructureException;
import org.eclipse.che.api.workspace.server.spi.environment.InternalEnvironmentFactory;
import org.eclipse.che.api.workspace.server.spi.environment.InternalMachineConfig;
import org.eclipse.che.api.workspace.server.spi.environment.InternalRecipe;
import org.eclipse.che.api.workspace.server.spi.environment.MachineConfigsValidator;
import org.eclipse.che.api.workspace.server.spi.environment.RecipeRetriever;

/** @author Sergii Leshchenko */
@Singleton
public class DockerfileEnvironmentFactory
    extends InternalEnvironmentFactory<DockerfileEnvironment> {

  @Inject
  public DockerfileEnvironmentFactory(
      InstallerRegistry installerRegistry,
      RecipeRetriever recipeRetriever,
      MachineConfigsValidator machinesValidator,
      @Named("che.workspace.default_memory_mb") long defaultMachineMemorySizeMB) {
    super(installerRegistry, recipeRetriever, machinesValidator, defaultMachineMemorySizeMB);
  }

  @Override
  protected DockerfileEnvironment doCreate(
      InternalRecipe recipe, Map<String, InternalMachineConfig> machines, List<Warning> warnings)
      throws InfrastructureException, ValidationException {
    if (!DockerfileEnvironment.TYPE.equals(recipe.getType())) {
      throw new ValidationException(
          format(
              "Dockerfile environment parser doesn't support recipe type '%s'", recipe.getType()));
    }
    String dockerfile = recipe.getContent();

    checkArgument(dockerfile != null, "Dockerfile content should not be null.");

    return new DockerfileEnvironment(dockerfile, recipe, machines, warnings);
  }
}
