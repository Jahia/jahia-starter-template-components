/*
 * ==========================================================================================
 * =                            JAHIA'S ENTERPRISE DISTRIBUTION                             =
 * ==========================================================================================
 *
 *                                  http://www.jahia.com
 *
 * JAHIA'S ENTERPRISE DISTRIBUTIONS LICENSING - IMPORTANT INFORMATION
 * ==========================================================================================
 *
 *     Copyright (C) 2002-2020 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */
package org.jahia.modules.sitebuilder.taglibs.mocks;

import org.apache.commons.lang.mutable.MutableInt;
import org.jahia.services.content.*;
import org.jahia.services.content.decorator.JCRFileContent;
import org.jahia.services.content.decorator.JCRPlaceholderNode;
import org.jahia.services.content.decorator.JCRSiteNode;
import org.jahia.services.content.decorator.JCRUserNode;
import org.jahia.services.content.nodetypes.ExtendedNodeDefinition;
import org.jahia.services.content.nodetypes.ExtendedNodeType;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;

import javax.jcr.*;
import javax.jcr.lock.Lock;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.security.AccessControlManager;
import javax.jcr.version.ActivityViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public class MockNode implements JCRNodeWrapper {

    private Map<String, MockProperty> props = new HashMap<String, MockProperty>();

    private String url;


    @Override public JCRPropertyWrapper setProperty(String s, Value[] values)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException
    {
        MockProperty prop = new MockProperty();
        prop.setValue(values);
        props.put(s, prop);

        return prop;
    }

    @Override public boolean hasProperty(String s) throws RepositoryException {
        MockProperty prop = props.get(s);
        return prop != null;
    }

    @Override public JCRPropertyWrapper getProperty(String s)
            throws PathNotFoundException, RepositoryException
    {
        return props.get(s);
    }


    @Override public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /** unimplemented methods */

    @Override public Node getRealNode() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRUserNode getUser() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value value)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value value, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value[] values, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String[] strings)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String[] strings, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, InputStream inputStream)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Binary binary)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, boolean b)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, double v)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, BigDecimal bigDecimal)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, long l)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Calendar calendar)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Node node)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper getNode(String s) throws PathNotFoundException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1, String s2) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getProperties() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getProperties(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getProperties(String[] strings) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRItemWrapper getPrimaryItem() throws ItemNotFoundException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getUUID() throws UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getIdentifier() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public int getIndex() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public PropertyIterator getReferences() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getReferences(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getWeakReferences() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public PropertyIterator getWeakReferences(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean hasNode(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean hasNodes() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean hasProperties() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public ExtendedNodeType getPrimaryNodeType() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public ExtendedNodeType[] getMixinNodeTypes() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return new ExtendedNodeType[0];
    }

    @Override public boolean isNodeType(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void setPrimaryType(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addMixin(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void removeMixin(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public boolean canAddMixin(String s) throws NoSuchNodeTypeException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public ExtendedNodeDefinition getDefinition() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Version checkin()
            throws VersionException, UnsupportedRepositoryOperationException, InvalidItemStateException, LockException,
            RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public void checkout()
            throws UnsupportedRepositoryOperationException, LockException, ActivityViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void doneMerge(Version version)
            throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void cancelMerge(Version version)
            throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void update(String s)
            throws NoSuchWorkspaceException, AccessDeniedException, LockException, InvalidItemStateException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public Map<String, List<String[]>> getAclEntries() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Map<String, Map<String, String>> getActualAclEntries() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Map<String, List<JCRNodeWrapper>> getAvailableRoles() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean hasPermission(String s) {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public Set<String> getPermissions() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public BitSet getPermissionsAsBitSet() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean grantRoles(String s, Set<String> set) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean denyRoles(String s, Set<String> set) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean changeRoles(String s, Map<String, String> map) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean revokeRolesForPrincipal(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean revokeAllRoles() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean getAclInheritanceBreak() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean setAclInheritanceBreak(boolean b) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public JCRNodeWrapper createCollection(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper uploadFile(String s, InputStream inputStream, String s1) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getAbsoluteUrl(ServletRequest servletRequest) {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getAbsoluteWebdavUrl(HttpServletRequest httpServletRequest) {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getWebdavUrl() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<String> getThumbnails() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getThumbnailUrl(String s) {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Map<String, String> getThumbnailUrls() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Map<String, String> getPropertiesAsString() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getPrimaryNodeTypeName() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<String> getNodeTypes() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean isCollection() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isFile() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isPortlet() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public Date getLastModifiedAsDate() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Date getLastPublishedAsDate() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Date getContentLastModifiedAsDate() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Date getContentLastPublishedAsDate() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Date getCreationDateAsDate() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getCreationUser() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getModificationUser() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getPublicationUser() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getLanguage() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getPropertyAsString(String s) {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<JCRItemWrapper> getAncestors() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean rename(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(String s, String s1) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(String s, String s1, NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy)
            throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b,
            NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map)
            throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, List<String> list, int i) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map, List<String> list,
            int i, MutableInt mutableInt) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map, List<String> list,
            int i, MutableInt mutableInt, NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy)
            throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void copyProperties(JCRNodeWrapper jcrNodeWrapper, Map<String, List<String>> map) throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public boolean lockAndStoreToken(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean lockAndStoreToken(String s, String s1) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public String getLockOwner() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Map<String, List<String>> getLockInfos() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean isLocked() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void followLifecycleTransition(String s)
            throws UnsupportedRepositoryOperationException, InvalidLifecycleTransitionException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public String[] getAllowedLifecycleTransistions() throws UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return new String[0];
    }

    @Override public Lock lock(boolean b, boolean b1)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Lock getLock()
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean holdsLock() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void unlock()
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void unlock(String s)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void unlock(String s, String s1)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void clearAllLocks() throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void checkLock() throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void versionFile() {
        // ${TODO} Auto-generated method stub

    }

    @Override public boolean isVersioned() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void checkpoint() {
        // ${TODO} Auto-generated method stub

    }

    @Override public List<VersionInfo> getVersionInfos() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<VersionInfo> getLinearVersionInfos() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<String> getVersions() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<Version> getVersionsAsVersion() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRStoreProvider getJCRProvider() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRStoreProvider getProvider() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRFileContent getFileContent() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public ExtendedPropertyDefinition getApplicablePropertyDefinition(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public ExtendedPropertyDefinition getApplicablePropertyDefinition(String s, int i, boolean b) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<ExtendedPropertyDefinition> getReferenceProperties() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public ExtendedNodeDefinition getApplicableChildNodeDefinition(String s, String s1)
            throws ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean isLockable() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public List<Locale> getLockedLocales() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper addNode(String s)
            throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException,
            RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper addNode(String s, String s1)
            throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException,
            ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public void orderBefore(String s, String s1)
            throws UnsupportedRepositoryOperationException, VersionException, ConstraintViolationException, ItemNotFoundException,
            LockException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public JCRNodeWrapper addNode(String s, String s1, String s2, Calendar calendar, String s3, Calendar calendar1, String s4)
            throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException,
            ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRPlaceholderNode getPlaceholder() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getName() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getPath() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Node getI18N(Locale locale) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Node getI18N(Locale locale, boolean b) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public NodeIterator getI18Ns() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean hasI18N(Locale locale) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean hasI18N(Locale locale, boolean b) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public Node getOrCreateI18N(Locale locale) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Node getOrCreateI18N(Locale locale, Calendar calendar, String s, Calendar calendar1, String s1)
            throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public List<Locale> getExistingLocales() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper clone(JCRNodeWrapper jcrNodeWrapper, String s)
            throws ItemExistsException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean checkValidity() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean checkLanguageValidity(Set<String> set) {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public JCRSiteNode getResolveSite() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean hasTranslations() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean checkI18nAndMandatoryPropertiesForLocale(Locale locale) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public String getDisplayableName() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getUnescapedName() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public AccessControlManager getAccessControlManager() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean canMarkForDeletion() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isMarkedForDeletion() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void markForDeletion(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void unmarkForDeletion() throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public JCRNodeIteratorWrapper getNodes() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeIteratorWrapper getNodes(String s) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeIteratorWrapper getNodes(String[] strings) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeIteratorWrapper getSharedSet() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public void removeSharedSet() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void removeShare() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public boolean isCheckedOut() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void restore(String s, boolean b)
            throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void restore(Version version, boolean b)
            throws VersionException, ItemExistsException, InvalidItemStateException, UnsupportedRepositoryOperationException, LockException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void restore(Version version, String s, boolean b)
            throws PathNotFoundException, ItemExistsException, VersionException, ConstraintViolationException,
            UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void restoreByLabel(String s, boolean b)
            throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public VersionHistory getVersionHistory() throws UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Version getBaseVersion() throws UnsupportedRepositoryOperationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeIteratorWrapper merge(String s, boolean b)
            throws NoSuchWorkspaceException, AccessDeniedException, MergeException, LockException, InvalidItemStateException,
            RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getCorrespondingNodePath(String s)
            throws ItemNotFoundException, NoSuchWorkspaceException, AccessDeniedException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRItemWrapper getAncestor(int i) throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRNodeWrapper getParent() throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public int getDepth() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public JCRSessionWrapper getSession() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean isNode() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isNew() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isModified() {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean isSame(Item item) throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public void accept(ItemVisitor itemVisitor) throws RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void save() throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException,
            ReferentialIntegrityException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void refresh(boolean b) throws InvalidItemStateException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void remove()
            throws VersionException, LockException, ConstraintViolationException, AccessDeniedException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void saveSession()
            throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException,
            ReferentialIntegrityException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public String getCanonicalPath() {
        // ${TODO} Auto-generated method stub
        return null;
    }
}
