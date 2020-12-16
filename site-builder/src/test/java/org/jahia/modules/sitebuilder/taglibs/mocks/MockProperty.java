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

import org.jahia.services.content.*;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.PropertyDefinition;
import javax.jcr.version.VersionException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

public class MockProperty implements JCRPropertyWrapper {

    private MockValue[] values;

    /** Only MockValues allowed */
    @Override public void setValue(Value[] values)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException
    {
        Class<?> componentType = values.getClass().getComponentType();
        if (MockValue.class.isAssignableFrom(componentType)) {
            this.values = (MockValue[]) values;
        }
    }

    @Override public JCRValueWrapper[] getValues() throws RepositoryException {
        return values;
    }


    /** unimplemented methods */

    @Override public void addValue(String s)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(InputStream inputStream)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(long l)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(double v)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(Calendar calendar)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(boolean b)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(Node node)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(Node node, boolean b)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(Binary binary)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(BigDecimal bigDecimal)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValue(Value value)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void addValues(Value[] values)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(Value value)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(String s)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(String[] strings)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(InputStream inputStream)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(Binary binary)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(long l)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(double v)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(BigDecimal bigDecimal)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(Calendar calendar)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(boolean b)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public void setValue(Node node)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub

    }

    @Override public JCRValueWrapper getValue() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getString() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public InputStream getStream() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Binary getBinary() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public long getLong() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public double getDouble() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public BigDecimal getDecimal() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Calendar getDate() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean getBoolean() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public Node getNode() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public Property getProperty() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public long getLength() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public long[] getLengths() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return new long[0];
    }

    @Override public PropertyDefinition getDefinition() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public int getType() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return 0;
    }

    @Override public boolean isMultiple() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public JCRValueWrapper getRealValue() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public JCRValueWrapper[] getRealValues() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return new JCRValueWrapper[0];
    }

    @Override public JCRNodeWrapper getContextualizedNode() throws ValueFormatException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getLocale() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public boolean removeValue(Value value)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public boolean removeValues(Value[] values)
            throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        // ${TODO} Auto-generated method stub
        return false;
    }

    @Override public Property getRealProperty() {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getPath() throws RepositoryException {
        // ${TODO} Auto-generated method stub
        return null;
    }

    @Override public String getName() throws RepositoryException {
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
