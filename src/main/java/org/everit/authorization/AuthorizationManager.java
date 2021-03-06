/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.authorization;

/**
 * Low level API to manage permissions and permission inheritances. The implementation should handle
 * JTA based distributed transactions.
 */
public interface AuthorizationManager {

  /**
   * Defining a new permission.
   *
   * @param authorizedResourceId
   *          The resource id of the entity who is authorized.
   * @param targetResourceId
   *          The resource id of the entity which we defined the action on.
   * @param action
   *          The action that can be made on the target resource.
   * @throws NullPointerException
   *           if the action parameter is null.
   */
  void addPermission(long authorizedResourceId, long targetResourceId, String action);

  /**
   * Specifying a new permission inheritance. The child will inherit all of the permissions of the
   * parent.
   *
   * @param parentResourceId
   *          The resource id of the parent entity (role, user group, ...).
   * @param childResourceId
   *          The resource id of the child entity (e.g. user).
   */
  void addPermissionInheritance(long parentResourceId, long childResourceId);

  /**
   * Clears the cache of the authorization component. This might be necessary only after a 3rdparty
   * component does a bulk update on the permission or permission inheritance table. E.g.: grant a
   * permission for all users who have a special attribute.
   */
  void clearCache();

  /**
   * Removes a permission definition. If there was no such permission, nothing will happen.
   *
   * @param authorizedResourceId
   *          The resource id of the entity who is authorized.
   * @param targetResourceId
   *          The resource id of the entity which we defined the action on.
   * @param action
   *          The action that can be made on the target resource.
   * @throws NullPointerException
   *           if the action parameter is null.
   */
  void removePermission(long authorizedResourceId, long targetResourceId, String action);

  /**
   * Removes a permission inheritance. If there was no such inheritance, nothing will happen.
   *
   * @param parentResourceId
   *          The resource id of the parent entity (role, user group, ...).
   * @param childResourceId
   *          The resource id of the child entity (e.g. user).
   */
  void removePermissionInheritance(long parentResourceId, long childResourceId);

}
