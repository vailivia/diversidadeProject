-- Wait for the database to be ready
DECLARE
  v_count NUMBER;
BEGIN
  LOOP
    BEGIN
      SELECT COUNT(*) INTO v_count FROM v$database WHERE status = 'OPEN';
      EXIT WHEN v_count > 0;
      DBMS_LOCK.SLEEP(5);
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_LOCK.SLEEP(5);
    END;
  END LOOP;
END;
/

-- Create the user if it doesn't exist
DECLARE
  user_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO user_exists FROM dba_users WHERE username = 'DIVERSIDADE';
  IF user_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE USER diversidade IDENTIFIED BY diversidade123';
    DBMS_OUTPUT.PUT_LINE('User DIVERSIDADE created successfully');
  ELSE
    DBMS_OUTPUT.PUT_LINE('User DIVERSIDADE already exists');
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error creating user: ' || SQLERRM);
    RAISE;
END;
/

-- Grant necessary privileges
BEGIN
  EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE, DBA TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE SESSION TO diversidade';
  EXECUTE IMMEDIATE 'GRANT UNLIMITED TABLESPACE TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE TABLE TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE SEQUENCE TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE VIEW TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE PROCEDURE TO diversidade';
  EXECUTE IMMEDIATE 'GRANT CREATE TRIGGER TO diversidade';
  DBMS_OUTPUT.PUT_LINE('Privileges granted successfully');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error granting privileges: ' || SQLERRM);
    RAISE;
END;
/

-- Create tablespace if it doesn't exist
DECLARE
  ts_exists NUMBER;
BEGIN
  SELECT COUNT(*) INTO ts_exists FROM dba_tablespaces WHERE tablespace_name = 'DIVERSIDADE_DATA';
  IF ts_exists = 0 THEN
    EXECUTE IMMEDIATE 'CREATE TABLESPACE DIVERSIDADE_DATA DATAFILE ''/opt/oracle/oradata/XE/DIVERSIDADE_DATA.dbf'' SIZE 100M AUTOEXTEND ON NEXT 10M';
    DBMS_OUTPUT.PUT_LINE('Tablespace DIVERSIDADE_DATA created successfully');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Tablespace DIVERSIDADE_DATA already exists');
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error creating tablespace: ' || SQLERRM);
    RAISE;
END;
/

-- Set default tablespace
BEGIN
  EXECUTE IMMEDIATE 'ALTER USER diversidade DEFAULT TABLESPACE DIVERSIDADE_DATA';
  DBMS_OUTPUT.PUT_LINE('Default tablespace set successfully');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error setting default tablespace: ' || SQLERRM);
    RAISE;
END;
/

-- Verify the setup
SELECT 'User DIVERSIDADE exists' as status FROM dba_users WHERE username = 'DIVERSIDADE'
UNION ALL
SELECT 'Tablespace DIVERSIDADE_DATA exists' FROM dba_tablespaces WHERE tablespace_name = 'DIVERSIDADE_DATA'; 