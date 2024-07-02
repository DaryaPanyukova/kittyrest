DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'owners'
                         AND column_name = 'id'
                         AND is_identity = 'YES') THEN
            ALTER TABLE owners
                ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;
        END IF;
    END
$$;
