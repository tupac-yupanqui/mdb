export interface LoginData {
    username: string;
    password: string;
}

export interface UserToken {
    token: string;
    type?: string;
    id: number;
    username: string;
    email?: string;
    roles: Array<string>;
}

export interface Artist {
    id?: number;
    name?: string;
}

export interface Album {
    id: number;
    name: string;
    release?: Date;
    cover?: string;
    coversmall?: string;
    subalbums? : Subalbum[]
}

export interface Subalbum {
    id: number;
    name: string;
}

export interface AlbumFilter {
    album?: string;
    artist?: string;
    year?: string;
    sort?: string;
    order?: string;
}