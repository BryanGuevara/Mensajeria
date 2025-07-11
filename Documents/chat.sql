-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-07-2025 a las 19:46:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS chat CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE chat;

CREATE TABLE `mensaje` (
  `GUID` char(36) NOT NULL DEFAULT uuid(),
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `userGUID` varchar(100) NOT NULL,
  `mensaje` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuarios` (
  `GUID` char(36) NOT NULL DEFAULT uuid(),
  `usuario` varchar(50) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`GUID`);

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`GUID`),
  ADD UNIQUE KEY `usuario` (`usuario`);
COMMIT;