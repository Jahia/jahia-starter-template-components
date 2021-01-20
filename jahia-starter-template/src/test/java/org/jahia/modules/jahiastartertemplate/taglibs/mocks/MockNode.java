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
 *     Copyright (C) 2002-2021 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms & Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */
package org.jahia.modules.jahiastartertemplate.taglibs.mocks;

import org.apache.commons.lang.mutable.MutableInt;
import org.jahia.services.content.*;
import org.jahia.services.content.decorator.JCRFileContent;
import org.jahia.services.content.decorator.JCRPlaceholderNode;
import org.jahia.services.content.decorator.JCRSiteNode;
import org.jahia.services.content.decorator.JCRUserNode;
import org.jahia.services.content.nodetypes.ExtendedNodeDefinition;
import org.jahia.services.content.nodetypes.ExtendedNodeType;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger log = LoggerFactory.getLogger(MockNode.class);

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
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRUserNode getUser() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value value)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value value, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Value[] values, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String[] strings)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String[] strings, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1, int i)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, InputStream inputStream)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Binary binary)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, boolean b)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, double v)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, BigDecimal bigDecimal)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, long l)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Calendar calendar)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, Node node)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper getNode(String s) throws PathNotFoundException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPropertyWrapper setProperty(String s, String s1, String s2) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getProperties() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getProperties(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getProperties(String[] strings) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRItemWrapper getPrimaryItem() throws ItemNotFoundException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getUUID() throws UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getIdentifier() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public int getIndex() throws RepositoryException {
        log.warn("Unimplemented methods");
        return 0;
    }

    @Override public PropertyIterator getReferences() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getReferences(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getWeakReferences() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyIterator getWeakReferences(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean hasNode(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean hasNodes() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean hasProperties() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public ExtendedNodeType getPrimaryNodeType() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public ExtendedNodeType[] getMixinNodeTypes() throws RepositoryException {
        log.warn("Unimplemented methods");
        return new ExtendedNodeType[0];
    }

    @Override public boolean isNodeType(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void setPrimaryType(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void addMixin(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void removeMixin(String s)
            throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public boolean canAddMixin(String s) throws NoSuchNodeTypeException, RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public ExtendedNodeDefinition getDefinition() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Version checkin()
            throws VersionException, UnsupportedRepositoryOperationException, InvalidItemStateException, LockException,
            RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public void checkout()
            throws UnsupportedRepositoryOperationException, LockException, ActivityViolationException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void doneMerge(Version version)
            throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void cancelMerge(Version version)
            throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void update(String s)
            throws NoSuchWorkspaceException, AccessDeniedException, LockException, InvalidItemStateException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public Map<String, List<String[]>> getAclEntries() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Map<String, Map<String, String>> getActualAclEntries() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Map<String, List<JCRNodeWrapper>> getAvailableRoles() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean hasPermission(String s) {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public Set<String> getPermissions() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public BitSet getPermissionsAsBitSet() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean grantRoles(String s, Set<String> set) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean denyRoles(String s, Set<String> set) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean changeRoles(String s, Map<String, String> map) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean revokeRolesForPrincipal(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean revokeAllRoles() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean getAclInheritanceBreak() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean setAclInheritanceBreak(boolean b) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public JCRNodeWrapper createCollection(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper uploadFile(String s, InputStream inputStream, String s1) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getAbsoluteUrl(ServletRequest servletRequest) {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getAbsoluteWebdavUrl(HttpServletRequest httpServletRequest) {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getWebdavUrl() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<String> getThumbnails() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getThumbnailUrl(String s) {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Map<String, String> getThumbnailUrls() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Map<String, String> getPropertiesAsString() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getPrimaryNodeTypeName() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<String> getNodeTypes() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean isCollection() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isFile() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isPortlet() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public Date getLastModifiedAsDate() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Date getLastPublishedAsDate() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Date getContentLastModifiedAsDate() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Date getContentLastPublishedAsDate() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Date getCreationDateAsDate() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getCreationUser() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getModificationUser() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getPublicationUser() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getLanguage() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getPropertyAsString(String s) {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<JCRItemWrapper> getAncestors() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean rename(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(String s, String s1) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(String s, String s1, NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy)
            throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b,
            NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map)
            throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, List<String> list, int i) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map, List<String> list,
            int i, MutableInt mutableInt) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean copy(JCRNodeWrapper jcrNodeWrapper, String s, boolean b, Map<String, List<String>> map, List<String> list,
            int i, MutableInt mutableInt, NodeNamingConflictResolutionStrategy nodeNamingConflictResolutionStrategy)
            throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void copyProperties(JCRNodeWrapper jcrNodeWrapper, Map<String, List<String>> map) throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public boolean lockAndStoreToken(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean lockAndStoreToken(String s, String s1) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public String getLockOwner() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Map<String, List<String>> getLockInfos() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean isLocked() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void followLifecycleTransition(String s)
            throws UnsupportedRepositoryOperationException, InvalidLifecycleTransitionException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public String[] getAllowedLifecycleTransistions() throws UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");
        return new String[0];
    }

    @Override public Lock lock(boolean b, boolean b1)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Lock getLock()
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean holdsLock() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void unlock()
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void unlock(String s)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void unlock(String s, String s1)
            throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void clearAllLocks() throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void checkLock() throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void versionFile() {
        log.warn("Unimplemented methods");

    }

    @Override public boolean isVersioned() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void checkpoint() {
        log.warn("Unimplemented methods");

    }

    @Override public List<VersionInfo> getVersionInfos() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<VersionInfo> getLinearVersionInfos() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<String> getVersions() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<Version> getVersionsAsVersion() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRStoreProvider getJCRProvider() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRStoreProvider getProvider() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRFileContent getFileContent() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public ExtendedPropertyDefinition getApplicablePropertyDefinition(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public ExtendedPropertyDefinition getApplicablePropertyDefinition(String s, int i, boolean b) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<ExtendedPropertyDefinition> getReferenceProperties() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public ExtendedNodeDefinition getApplicableChildNodeDefinition(String s, String s1)
            throws ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean isLockable() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public List<Locale> getLockedLocales() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper addNode(String s)
            throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException,
            RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper addNode(String s, String s1)
            throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException,
            ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public void orderBefore(String s, String s1)
            throws UnsupportedRepositoryOperationException, VersionException, ConstraintViolationException, ItemNotFoundException,
            LockException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public JCRNodeWrapper addNode(String s, String s1, String s2, Calendar calendar, String s3, Calendar calendar1, String s4)
            throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException,
            ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRPlaceholderNode getPlaceholder() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getName() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getPath() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Node getI18N(Locale locale) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Node getI18N(Locale locale, boolean b) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public NodeIterator getI18Ns() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean hasI18N(Locale locale) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean hasI18N(Locale locale, boolean b) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public Node getOrCreateI18N(Locale locale) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Node getOrCreateI18N(Locale locale, Calendar calendar, String s, Calendar calendar1, String s1)
            throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public List<Locale> getExistingLocales() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper clone(JCRNodeWrapper jcrNodeWrapper, String s)
            throws ItemExistsException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean checkValidity() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean checkLanguageValidity(Set<String> set) {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public JCRSiteNode getResolveSite() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean hasTranslations() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean checkI18nAndMandatoryPropertiesForLocale(Locale locale) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public String getDisplayableName() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getUnescapedName() {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public AccessControlManager getAccessControlManager() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean canMarkForDeletion() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isMarkedForDeletion() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void markForDeletion(String s) throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void unmarkForDeletion() throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public JCRNodeIteratorWrapper getNodes() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeIteratorWrapper getNodes(String s) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeIteratorWrapper getNodes(String[] strings) throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeIteratorWrapper getSharedSet() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public void removeSharedSet() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void removeShare() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public boolean isCheckedOut() throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void restore(String s, boolean b)
            throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void restore(Version version, boolean b)
            throws VersionException, ItemExistsException, InvalidItemStateException, UnsupportedRepositoryOperationException, LockException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void restore(Version version, String s, boolean b)
            throws PathNotFoundException, ItemExistsException, VersionException, ConstraintViolationException,
            UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void restoreByLabel(String s, boolean b)
            throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public VersionHistory getVersionHistory() throws UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Version getBaseVersion() throws UnsupportedRepositoryOperationException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeIteratorWrapper merge(String s, boolean b)
            throws NoSuchWorkspaceException, AccessDeniedException, MergeException, LockException, InvalidItemStateException,
            RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getCorrespondingNodePath(String s)
            throws ItemNotFoundException, NoSuchWorkspaceException, AccessDeniedException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRItemWrapper getAncestor(int i) throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public JCRNodeWrapper getParent() throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public int getDepth() throws RepositoryException {
        log.warn("Unimplemented methods");
        return 0;
    }

    @Override public JCRSessionWrapper getSession() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean isNode() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isNew() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isModified() {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public boolean isSame(Item item) throws RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public void accept(ItemVisitor itemVisitor) throws RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void save() throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException,
            ReferentialIntegrityException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void refresh(boolean b) throws InvalidItemStateException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void remove()
            throws VersionException, LockException, ConstraintViolationException, AccessDeniedException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public void saveSession()
            throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException,
            ReferentialIntegrityException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        log.warn("Unimplemented methods");

    }

    @Override public String getCanonicalPath() {
        log.warn("Unimplemented methods");
        return null;
    }
}
