CREATE TABLE Candidates (
    id INT IDENTITY (1, 1) PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    votes INT NOT NULL CHECK (votes > 4)
);

-- ������� ������ � ����������
INSERT INTO Candidates (last_name, votes)
VALUES
('Ivanov', 1200),
('Petrov', 950),
('Sidorov', 1100),
('Smirnov', 780);

-- �������� ������
SELECT * FROM Candidates;