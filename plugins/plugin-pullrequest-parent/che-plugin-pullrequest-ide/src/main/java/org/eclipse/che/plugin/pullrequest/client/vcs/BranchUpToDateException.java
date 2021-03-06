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
package org.eclipse.che.plugin.pullrequest.client.vcs;

import javax.validation.constraints.NotNull;

/**
 * Exception raised when the branch pushed is up to date.
 *
 * @author Kevin Pollet
 */
public class BranchUpToDateException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an instance of {@link BranchUpToDateException}.
   *
   * @param branchName the branch name.
   */
  public BranchUpToDateException(@NotNull final String branchName) {
    super("Branch '" + branchName + "' is up-to-date");
  }
}
